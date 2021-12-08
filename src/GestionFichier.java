package jeu.dameDePique;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.*;
import java.util.*;
/**
 * TODO Commenter la responsabilité de cette classe
 * @author Loup Salome
 *
 */
public class GestionFichier {

    private static final int MARQUEUR_DISTRIBUTION= -1;
    private static final int MARQUEUR_SCORE = -2;
    private static final int MARQUEUR_PLI = -3;
    private static final int MARQUEUR_INFOJOUEUR = -4;

    private static final int ESPACEMENT_SEEK = 4;
    private static final int ESPACEMENT_SEEK_2 = 8;
    private static final int ESPACEMENT_SEEK_3_NOM_PSEUDO = 50;

    private static final int NOMBRE_CARTES = 52;

    private static final int DEBUT_DISTRIBUTION = 0;

    private static final int DEBUT_SCORE = 232;

    private static final int DEBUT_CENTRE_TABLE = 432;

    private static final int DEBUT_PSEUDO = 632;

    private static final int NOMBRE_CARTES_JOUEUR = 13;
    private static final int NOMBRE_JOUEUR = 4;
    private static final int NOMBRE_CARTE_JEU = 52;


    private static void ecrireScoreDepuisTexte(String nomFichierBinaire , String nomFichierTexte) throws IOException {

        Scanner entree = new Scanner(new File(nomFichierTexte) );
        RandomAccessFile lecture = new RandomAccessFile(nomFichierBinaire , "rw");

        int rang = DEBUT_SCORE;
        int entierLu = 0;
        rang += ESPACEMENT_SEEK_2;
        boolean stop = false;

        while(!stop) {
            if(entree.hasNextInt()) {
                entierLu = entree.nextInt();
                stop = entierLu == MARQUEUR_SCORE;
            }else {
                entree.next();
            }
        }
        /* On fait le chargement dans le fichier binaire  */
        stop = false;
        while(!stop) {
            if(entree.hasNextInt()) {
                entierLu = entree.nextInt();
                stop = entierLu == MARQUEUR_SCORE;
                do {
                    if(entree.hasNextInt() && !stop) {
                        entierLu = entree.nextInt();
                        lecture.seek(rang);
                        lecture.writeInt(entierLu);
                        rang += ESPACEMENT_SEEK_2;
                    }else {
                        entree.next();
                    }
                }while(!entree.hasNextInt());
            }else {
                entree.next();
            }
        }
    }

    /**
     * TODO Commenter le rôle de cette méthode (Commande ou requête ?)
     * @param nomFichierBinaire
     * @param nomFichierTexte
     * @throws IOException
     */
    public static void ecrireDistributionDepuisTexte(String nomFichierBinaire, String nomFichierTexte) throws IOException {

        Scanner entree = new Scanner(new File(nomFichierTexte) );
        RandomAccessFile lecture = new RandomAccessFile(nomFichierBinaire, "rw");

        int rang = DEBUT_DISTRIBUTION;
        int entierLu = 0;
        rang += ESPACEMENT_SEEK;
        boolean stop = false;
        stop = false;

        /* On positionne le scanner  */
        while(!stop) {
            if(entree.hasNextInt()) {
                entierLu = entree.nextInt();
                stop = entierLu == MARQUEUR_DISTRIBUTION;
            }else {
                entree.next();
            }
        }

        /* On fait le chargement dans le fichier binaire  */
        stop = false;
        while(!stop) {
            if(entree.hasNextInt()) {
                entierLu = entree.nextInt();
                stop = entierLu == MARQUEUR_DISTRIBUTION;
                if(!stop && entierLu >= 102 && entierLu <= 414 ) {
                    lecture.seek(rang);
                    lecture.writeInt(entierLu);
                    rang += ESPACEMENT_SEEK;
                }else {
                    entree.next();
                }
            }else {
                entree.next();
            }
        }
    }

    private static void ecrireCentreDepuisTexte(String nomFichierBinaire , String nomFichierTexte) throws IOException {

        Scanner entree = new Scanner(new File(nomFichierTexte) );
        RandomAccessFile lecture = new RandomAccessFile(nomFichierBinaire , "rw");
        int rang = DEBUT_CENTRE_TABLE;
        int entierLu = 0;
        rang += ESPACEMENT_SEEK_2;
        boolean stop = false;
        boolean occurence;

        while(!stop) {
            if(entree.hasNextInt()) {
                entierLu = entree.nextInt();
                stop = entierLu == MARQUEUR_PLI ;
            }else {
                entree.next();
            }
        }
        stop = false;
        while(!stop) {
            if(entree.hasNextInt()) {
                entierLu = entree.nextInt();
                stop = entierLu == MARQUEUR_PLI;
                do {
                    if(entree.hasNextInt() && !stop) {
                        entierLu = entree.nextInt();
                        lecture.seek(rang);
                        lecture.writeInt(entierLu);
                        rang += ESPACEMENT_SEEK_2;
                    }else {
                        entree.next();
                    }
                }while(!entree.hasNextInt());
            }else {
                entree.next();
            }
        }
        entree.close();
        lecture.close();
    }


    /**
     * TODO Commenter le rôle de la methode
     */
    public static void ecrireDansLeFichierBinaireDepuisTexte(String fichierBinaire , String fichierTexte) throws IOException {

        /* TODO ÉCRIRE DEPUIS LE FICHIER TEXTE  */
        ecrireCentreDepuisTexte(fichierBinaire , fichierTexte);
        ecrireDistributionDepuisTexte(fichierBinaire , fichierTexte);
        ecrireScoreDepuisTexte(fichierBinaire , fichierTexte);

    }

    /**
     * TODO Commenter le rôle de la methode
     * @param fichierBinaire
     * @param fichierTexte
     * @throws IOException
     */
    public static void ecrireDansLeFichierTexte(String fichierBinaire , String fichierTexte ) throws IOException {


        RandomAccessFile ecritureBinaire = new RandomAccessFile(fichierBinaire , "rw");
        PrintWriter ecritureTexte = new PrintWriter(new File(fichierTexte) );

        int rang = 0;
        int entierLu = 0;
        int iteration = 0;
        int compteurJoueur = 0;
        boolean stop = false;
        boolean pseudoValable = false;
        String chaineLu = "";
        /* ------------------------- ECRITURE DISTRIBUTION  -------------------- */

        ecritureTexte.write(" Ici nous avons l'enregistrement de tout les joueurs \n\n\n ");
        ecritureTexte.write("" + MARQUEUR_DISTRIBUTION );
        ecritureTexte.write("\n\n\n");

        rang += ESPACEMENT_SEEK;
        ecritureBinaire.seek(rang);

        while(ecritureBinaire.readInt() != MARQUEUR_DISTRIBUTION) {
            ecritureBinaire.seek(rang);
            if(iteration % 13 == 0) {
                compteurJoueur++;
                ecritureTexte.write("\n Main du Joueur " + compteurJoueur);
            }
            ecritureTexte.write("\n carte du Joueur " + compteurJoueur + " est  " + ecritureBinaire.readInt());
            rang += ESPACEMENT_SEEK;
            iteration++;
        }

        ecritureTexte.write("\n\n");
        ecritureTexte.write("" + MARQUEUR_DISTRIBUTION);
        ecritureTexte.write("\n\n  FIN DE L ENREGISTREMENT DES DISTRIBUTION DES DIFFERENTS JOUEURS \n\n ");

        /* ------------------  FIN ÉCRITURE DISTRIBUTION  ------------------------------- */

        /* ------------------ DEBUT ÉCRITURE DES SCORES --------------------------------------------- */

        rang = DEBUT_SCORE;
        rang += ESPACEMENT_SEEK_2;

        ecritureTexte.write("\n\n DEBUT ENREGISTREMENT DES SCORES   \n\n");
        ecritureTexte.write("" + MARQUEUR_SCORE);
        ecritureTexte.write("\n\n");

        ecritureBinaire.seek(rang);

        while(ecritureBinaire.readInt() != MARQUEUR_SCORE) {
            ecritureBinaire.seek(rang);
            ecritureTexte.write("\n score du joueur " +  ecritureBinaire.readInt() );
            rang += ESPACEMENT_SEEK_2;
            ecritureBinaire.seek(rang);
            ecritureTexte.write(" est   " +  ecritureBinaire.readInt() );
            rang += ESPACEMENT_SEEK_2;
        }

        ecritureTexte.write("\n FIN ENREGISTREMENT DES SCORES \n");
        ecritureTexte.write("" + MARQUEUR_SCORE);
        ecritureTexte.write("\n\n");

        /* ------------------ FIN  ECRITURE DES SCORES --------------------------------------------- */

        /* ------------------ ECRITURE CENTRE DE TABLE  --------------------------------------------- */

        ecritureTexte.write("\n\n");
        ecritureTexte.write(" DEBUT DE L ENREGISTREMENT DES CENTRES DE TABLE   ");

        ecritureTexte.write("\n\n");
        ecritureTexte.write("" + MARQUEUR_PLI);
        ecritureTexte.write("\n\n");

        rang = DEBUT_CENTRE_TABLE;
        rang += ESPACEMENT_SEEK_2;
        for(iteration = 0 ; iteration < NOMBRE_JOUEUR ; iteration++ ) {

            ecritureBinaire.seek(rang);
            ecritureTexte.write("carte posée au centre par le joueur " + iteration + "  " + ecritureBinaire.readInt() + "\n" );
            rang += ESPACEMENT_SEEK_2;
        }
        ecritureTexte.write("\n\n");
        ecritureTexte.write("" + MARQUEUR_PLI);
        ecritureTexte.write("\n\n");
        ecritureTexte.write(" FIN DE L ENREGISTREMENT DES CENTRES DE TABLE  ");

        /* ------------------ FIN ECRITURE CENTRE DE TABLE  --------------------------------------------- */

        /* ------------------ ECRITURE DES INFORMATIONS DES JOUEURS  --------------------------------------------- */

        ecritureTexte.write("\n\n");
        ecritureTexte.write("DEBUT DE L ENREGISTREMENT DES PSEUDOS ");
        ecritureTexte.write("\n\n");
        ecritureTexte.write("" + MARQUEUR_INFOJOUEUR);
        ecritureTexte.write("\n\n");


        /* écrire les pseudos depuis le fichier binaire jusqu'au fichier texte TODO  */

        /* Apres de multiples essais on pense que un Scanner convient mieux */

        rang = DEBUT_PSEUDO;
        rang += ESPACEMENT_SEEK_3_NOM_PSEUDO*2;

        for(iteration = 0 ; iteration < NOMBRE_JOUEUR ; iteration++) {
            ecritureBinaire.seek(rang);
            ecritureTexte.write("\n le nom du joueur  " + iteration + " : " + ecritureBinaire.readLine());
            rang += ESPACEMENT_SEEK_3_NOM_PSEUDO;
        }

        ecritureTexte.write("\n\n");
        ecritureTexte.write("" + MARQUEUR_INFOJOUEUR);
        ecritureTexte.write("\n\n");
        ecritureTexte.write("FIN DE L ENREGISTREMENT DES PSEUDOS ");

        ecritureTexte.close();
        ecritureBinaire.close();
        /* ------------------ FIN ECRITURE DES INFORMATIONS DES JOUEURS  --------------------------------------------- */
    }

    /* ----------------------------------------- FIN DE LA ZONE PRIVEE -----------------------------------------------------------------  */
    /**
     * TODO Commenter le rôle de la methode
     * @param tableauJoueur
     * @param nomFichier
     * @throws IOException
     */
    public static void enregistrerDistribution(Joueur [] tableauJoueur , String nomFichier) throws IOException {

        RandomAccessFile ecriture = new RandomAccessFile(nomFichier , "rw");
        boolean stop = false;
        int i = 0;
        int y = 0;
        int rang = DEBUT_DISTRIBUTION;
        int entierLu = 0;
        boolean marquer = false;


        ecriture.seek(rang);
        ecriture.writeInt(MARQUEUR_DISTRIBUTION);

        rang += ESPACEMENT_SEEK;

        for(i = 0 ; i < tableauJoueur.length ; i++) {
            for(y = 0 ; y < tableauJoueur[i].mainJoueur().length ; y++) {

                ecriture.seek(rang);
                ecriture.writeInt(tableauJoueur[i].mainJoueur()[y].getNumCarte());
                rang += ESPACEMENT_SEEK;
            }
        }
        ecriture.writeInt(MARQUEUR_DISTRIBUTION);
        ecriture.close();
    }

    /**
     * TODO Commenter le r�le de la methode
     * @param tableauJoueur
     * @param nomFichier
     * @throws IOException
     */
    public static void chargerDistribution(Joueur [] tableauJoueur , String nomFichier) throws IOException {

        int rang = 0;
        int entierLu = 0;
        Carte ensembleCarte[][] = new Carte[NOMBRE_JOUEUR][NOMBRE_CARTES_JOUEUR];
        int i = 0;
        int y = 0;
        RandomAccessFile ecriture = new RandomAccessFile(nomFichier , "rw");


        for( i = 0 ; i  < ensembleCarte.length ; i++) {
            for( y = 0 ; y < ensembleCarte[i].length ; y++) {
                rang += ESPACEMENT_SEEK;
                ecriture.seek(rang);
                entierLu = ecriture.readInt();
                if(entierLu >= 0) {
                    ensembleCarte[i][y] = new Carte(entierLu);
                }
            }
        }

        for(i = 0 ; i < tableauJoueur.length ; i++) {
            tableauJoueur[i].creerMain(ensembleCarte[i]);
        }

    }

    /**
     * TODO Commenter le rôle de la methode
     * @param tableauJoueur
     * @throws IOException
     */
    public static void enregistrerScore(Joueur tableauJoueur[] , String nomFichier) throws IOException {

        RandomAccessFile ecriture = new RandomAccessFile(nomFichier , "rw");

        int entierLu = 0;
        int rang = DEBUT_SCORE;
        int i = 0;
        int y = 0;
        int sauvegarde = 0;
        int tableauScore[] = new int [4];
        boolean stop = false;
        boolean arret = false;
        ecriture.seek(rang);
        ecriture.writeInt(MARQUEUR_SCORE);

        for(i = 0 ; i < tableauJoueur.length ; i++) {
            tableauScore[i] = tableauJoueur[i].getScore();
        }

        /* ici on tri le score afin de l'afficher dans l'ordre decroissant dans le fichier */
        for(i = tableauScore.length-1 ; i > 1 ; i--) {
            stop = false;
            do {
                for(y = i-1 ; y >= 0 && !stop ; y--) {
                    stop = tableauScore[y] < tableauScore[i];
                }
                y++;
                sauvegarde = stop ? tableauScore[y] : 0;
                for(y = y ; y < i && stop  ; y++) {
                    tableauScore[y] = tableauScore[y+1];
                }
                tableauScore[i] = stop ? sauvegarde : tableauScore[i];
                stop = !stop;
            }while(!stop);
        }

        /* une fois le tri effectue on ecrit le score dans le fichier */

        for(i = 0 ; i < tableauScore.length  ; i++) {

            for(y = 0 ; y < tableauJoueur.length ; y++) {
                if(tableauJoueur[y].getScore() == tableauScore[i]) {

                    rang += ESPACEMENT_SEEK_2;
                    ecriture.seek(rang);
                    ecriture.writeInt(tableauJoueur[y].getPosition());
                    rang += ESPACEMENT_SEEK_2;
                    ecriture.seek(rang);
                    ecriture.writeInt(tableauScore[i]);
                }
            }
        }

        rang += ESPACEMENT_SEEK;
        ecriture.seek(rang);
        ecriture.writeInt(MARQUEUR_SCORE);
        ecriture.close();

    }



    /**
     * TODO Commenter le rôle de la methode
     * @throws IOException
     */
    public static Joueur [] chargerScore(Joueur tableauJoueur[] , String nomFichier) throws IOException {
        int entierLu = 0;
        int entierLu2 = 0;
        int rang = DEBUT_SCORE;
        int i = 0;
        RandomAccessFile lecture = new RandomAccessFile(nomFichier , "r");
        for(i = 0 ; i < tableauJoueur.length ; i++) {
            rang += ESPACEMENT_SEEK_2 ;
            lecture.seek(rang);
            entierLu = lecture.readInt();
            System.out.println(" la fonction " + entierLu);
            rang += ESPACEMENT_SEEK_2;
            lecture.seek(rang);
            entierLu2 = lecture.readInt();
            System.out.println(" avoir peur " + entierLu2);
            tableauJoueur[entierLu].modifScore(entierLu2);

        }

        tableauJoueur[1].modifScore(1480);
        lecture.close();

        return tableauJoueur;
    }

    /**
     * TODO Commenter le rôle de la methode
     * @throws IOException
     */
    public static void enregistrerPli(int [] tableauPli , String nomFichier) throws IOException {

        RandomAccessFile ecriture = new RandomAccessFile(nomFichier , "rw");

        int rang = DEBUT_CENTRE_TABLE;
        int i = 0;
        ecriture.seek(rang);
        ecriture.writeInt(MARQUEUR_PLI);
        rang += ESPACEMENT_SEEK_2;

        for(i = 0 ; i < tableauPli.length ; i++) {
            ecriture.seek(rang);
            ecriture.writeInt(tableauPli[i]);
            rang += ESPACEMENT_SEEK_2;
        }
        ecriture.seek(rang);
        ecriture.writeInt(MARQUEUR_PLI);
    }

    /**
     * TODO Commenter le rôle de la methode
     * @param tableauJoueur
     * @param nomFichier
     * @throws IOException
     */
    public static void enregistrerInformationJoueur(Joueur [] tableauJoueur , String nomFichier) throws IOException {

        int rang = DEBUT_PSEUDO;
        int iteration = 0;
        String transition = "";


        RandomAccessFile ecriture = new RandomAccessFile(nomFichier , "rw");

        ecriture.seek(DEBUT_PSEUDO);
        ecriture.writeInt(MARQUEUR_INFOJOUEUR);

        rang += ESPACEMENT_SEEK_3_NOM_PSEUDO;

        ecriture.seek(rang);
        ecriture.writeChars("\n");

        rang += ESPACEMENT_SEEK_3_NOM_PSEUDO;


        for(iteration = 0 ; iteration < tableauJoueur.length ; iteration++) {

            ecriture.seek(rang);


            ecriture.writeChars(tableauJoueur[iteration].getNom()  + "\n");

            ecriture.seek(rang);
            System.out.println(ecriture.readLine());

            rang += ESPACEMENT_SEEK_3_NOM_PSEUDO;
        }

        ecriture.seek(rang);
        ecriture.writeInt(MARQUEUR_INFOJOUEUR);
    }

    /**
     * TODO Commenter le rôle de la methode
     * @param nomFichier
     * @throws IOException
     */
    public static void afficherCentreTableFichier(String nomFichier) throws IOException {

        RandomAccessFile lecture = new RandomAccessFile(nomFichier , "r");
        int rang = DEBUT_CENTRE_TABLE;
        rang += ESPACEMENT_SEEK_2;
        boolean stop = false;
        int entierLu = 0;
        int iteration = 0;

        while(!stop) {
            lecture.seek(rang);
            entierLu = lecture.readInt();
            stop = entierLu == MARQUEUR_PLI;
            if(!stop) {
                System.out.println(" le joueur  " + iteration +
                        " a mis la carte : " + entierLu);
            }
            rang += ESPACEMENT_SEEK_2;
        }
        lecture.close();
    }
    /**
     * TODO Commenter le rôle de la methode
     */
    public static void afficherPseudoDansLeFichier(String nomFichier) throws IOException {

        RandomAccessFile lecture = new RandomAccessFile(nomFichier , "r");
        int rang = DEBUT_PSEUDO;
        rang += ESPACEMENT_SEEK_3_NOM_PSEUDO*2;
        String chaineLu = "";
        boolean stop = false;
        int iteration = 0;

        for(iteration = 0 ;  iteration < NOMBRE_JOUEUR ; iteration++ ) {
            lecture.seek(rang);
            chaineLu = lecture.readLine();
            System.out.println(" le joueur " + iteration + " se nomme : " + chaineLu);
            rang += ESPACEMENT_SEEK_3_NOM_PSEUDO;
        }
        lecture.close();
    }

    /**
     * TODO Commenter le rôle de la methode
     * @throws IOException
     */
    public static void afficherScoreFichier(String nomFichier) throws IOException {

        boolean stop = false;
        int entierLu = 0;
        int iteration = 0;
        int rang = DEBUT_SCORE;
        rang += ESPACEMENT_SEEK_2*2;

        RandomAccessFile lecture = new RandomAccessFile(nomFichier , "r");

        for(iteration = 0 ; iteration < NOMBRE_JOUEUR ; iteration++) {
            lecture.seek(rang);
            System.out.println( " le score du joueur : " +
                    iteration + " est : " + lecture.readInt());
            rang += ESPACEMENT_SEEK_2*2;
        }
    }

    /**
     * TODO Commenter le rôle de la methode
     * @throws IOException
     */
    public static void afficherDistributionDansLeFichier(String nomFichier) throws IOException{

        RandomAccessFile ecriture = new RandomAccessFile(nomFichier , "r");
        int entierLu = 0;
        int rang = DEBUT_DISTRIBUTION;
        int i = 0;
        int compteurJoueur = 0;
        boolean stop = false;

        ecriture.seek(rang);
        while(!stop ) {
            rang += ESPACEMENT_SEEK;
            if(i % 13 == 0 ) {
                compteurJoueur++;
                System.out.println(" LA MAIN DU JOUEUR " + compteurJoueur);
            }
            i++;
            ecriture.seek(rang);
            entierLu = ecriture.readInt();

            System.out.println(" la carte " + i + " vaut " + entierLu);
            if(entierLu == MARQUEUR_DISTRIBUTION) {
                stop = true;
            }
        }
    }
}
