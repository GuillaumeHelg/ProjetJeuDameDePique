/* GestionPartie.java                                              20 avr. 2021
 *
 * IUT Rodez, aucun droit d'auteur
 */

package jeu.dameDePique;

import java.io.IOException;
import java.util.Scanner;

//import java.util.Arrays;

/**
 * Cette classe se lancera quand on voudra débuter une partie.
 * Elle contient toutes les méthodes nescessaire au déroulement d'une partie :
 * -lancerPartie ----> permet de démarrer une partie
 * -DonnerCarte ---> permet de donner une carte à un autre joueur
 * -poserCarte ----> permet de poser une carte au centre
 * -quiGagneLePli ----> permet de savoir qui gagne un pli et attribut les pts
 * -quitterLaPartie ----> permet de sortir du jeu
 * @author Romain
 *
 */
public class GestionPartie {

    private static Carte[] centreTable = {new Carte(0), new Carte(0), new Carte (0), new Carte(0)};

    /**
     * Lance une partie du Jeu de la Dame de Pique
     * Se termine lorsque quelqu'un atteints 100 pts.
     * La partie se déroule comme ceci :
     * -Etape 1 : on distribue les cartes
     * -Etape 2 : on donne 3 cartes au joueur à sa droite
     * -Etape 3 : chaque joueur pose une carte au centre
     * -Etape 4, on vérifie qui a gagné et on attribue les pts
     * -Etape 5 : on répète l'étape 3 jusqu'à ce qu'on ai plus de carte en main
     * -Etape 6, on répete l'étape 1 jusqu'à ce que quelqu'un ai dépassé 100 pts
     *  @param enregistrement = 1 si on veut enregistrer la partie.
     * @throws InterruptedException
     * @throws IOException
     */
    public static void lancerPartie(boolean enregistrement) throws InterruptedException, IOException {

        Scanner clavier = new Scanner(System.in);

        /* compte le nb de coeur de chaque personnnes*/
        int[] coeurParPersonne = new int[4];

        String nomFichier;
        int numManche;
        int joueurEnCour;
        int nbCoeurAuCentre;
        boolean coeurTombe;
        boolean saisieCorrect;
        Joueur premierJoueur; // le joueur qui commence à jouer, est compris entre 1 et 4
        Joueur joueurPerdant; // le joueur qui a perdu la manche

        String pseudo;

        pseudo = "Utilisateur"; // bouchon
        numManche = 0;
        joueurPerdant = null; //bouchon

        if (enregistrement) {
            System.out.print("Veuillez saisir le nom du fichier qui enregistrera la partie : ");
            nomFichier = clavier.next();
            clavier.nextLine();
            nomFichier += ".bin";
        }


        System.out.println("\n|==================================|\n" +
                "|======| DEBUT DE LA PARTIE |======|\n" +
                "|==================================|\n");

        // Demandez le pseudo du joueur humain
        saisieCorrect = false;
        do {
            System.out.print("Veuillez saisir votre pseudo sans espace : ");
            pseudo = clavier.next();
            clavier.nextLine();
            if (pseudo.length() >= 4 && 15 >= pseudo.length()) {
                saisieCorrect = true;
            } else  {
                System.out.println("le pseudo doit être compris"
                        + " entre 4 et 15 charactères."
                        + " Veuillez recommencer la saisie.");
            }
        } while (!saisieCorrect);

        System.out.println("\nBienvenue " + pseudo + " !!\n");

        /*Création des 4 joueurs*/
        JoueurHumain Humain = new JoueurHumain(0, pseudo);
        JoueurOrdi Ordi1 = new JoueurOrdi(1, "Ordi_Est");
        JoueurOrdi Ordi2 = new JoueurOrdi(2, "Ordi_Nord");
        JoueurOrdi Ordi3 = new JoueurOrdi(3, "Ordi_Ouest");
        Joueur[] ordreDePassage = {Humain, Ordi1, Ordi2, Ordi3};

        /* tant que les scores de chaques joueur < 100 */
        boolean finDePartie = false;
        while (!finDePartie) {

            /* On remet à 0 le compte des coeurs */
            for (int i = 0; i < coeurParPersonne.length; i ++) {
                coeurParPersonne[i] = 0;
            }

            /* On distribue les cartes */
            GestionDistribution.distribuerCartes(Humain, Ordi1, Ordi2, Ordi3);

            if(enregistrement) {
                /*Enregistrer la distribution des cartes*/
            }

            System.out.print("Veuillez choisir 3 cartes à échanger ");
            /*On Echange 3 carte avec les autres joueurs*/
            echangeCarte(numManche, Humain, Ordi1, Ordi2, Ordi3);
            premierJoueur = recherche2Trefles(ordreDePassage);

            if(enregistrement) {
                /*Enregistrer les échanges de cartes*/
            }

            System.out.println("\nEchange de Carte Terminé !");
            System.out.println(premierJoueur.getNom() + " possède le 2 de Trèfle."
                    + " C'est donc à lui de commencer à jouer.\n\n");

            coeurTombe = false;
            for (int numPli = 1; numPli <= 13; numPli ++) {

                /*On enleve les cartes au centre de la table*/
                for (int indexCentre = 0; indexCentre <4; indexCentre ++) {
                    centreTable[indexCentre] = new Carte(0);
                }

                /*Chaque joueur pose une carte*/
                joueurEnCour = premierJoueur.getPosition();
                nbCoeurAuCentre = 0;
                for(int i = 0; i < 4; i ++) {

                    /* On vérifie si un coeur est déjà tombé */
                    if (!coeurTombe) {
                        for (int indexCentre = 0; indexCentre < 4; indexCentre ++) {
                            if(centreTable[indexCentre].couleur().equals("Coeur")) {
                                coeurTombe = true;
                            }
                        }
                    }

                    /* Le joueur à qui c'est le tour pose une carte */
                    joueurEnCour = joueurEnCour % 4;
                    switch (joueurEnCour) {
                        case 0 -> {
                            /*Afficher le centre*/
                            GestionAffichage.afficherCentre(centreTable, premierJoueur.getPosition());

                            /*Afficher la main*/
                            GestionAffichage.afficherMainJoueur(Humain.mainJoueur());
                            centreTable[joueurEnCour] = Humain.carteAPoser(centreTable, numPli, coeurTombe,premierJoueur.getPosition());
                        }
                        case 1 -> {
                            centreTable[joueurEnCour] =
                                    Ordi1.choisirCarteAPoser(centreTable, numPli, coeurTombe,premierJoueur.getPosition());
                        }
                        case 2 -> {
                            centreTable[joueurEnCour] = Ordi2.choisirCarteAPoser(centreTable, numPli, coeurTombe,premierJoueur.getPosition());
                        }
                        case 3 -> {
                            centreTable[joueurEnCour] = Ordi3.choisirCarteAPoser(centreTable, numPli, coeurTombe,premierJoueur.getPosition());
                        }
                    }

                    if (centreTable[joueurEnCour].couleur().equals("Coeur")
                            || centreTable[joueurEnCour].getNumCarte() == 412) {

                        nbCoeurAuCentre ++;
                    }

                    joueurEnCour ++;

                }



                /*Afficher le centre*/
                GestionAffichage.afficherCentre(centreTable, premierJoueur.getPosition());
                if(enregistrement) {
                    // enregistrer le centre
                }

                /*On vérifie qui gagne le pli*/
                premierJoueur = quiGagneLePli(centreTable, premierJoueur.getPosition(), ordreDePassage);
                System.out.println("\n" + premierJoueur.getNom() + " remporte le pli ! "
                        + "Son score  passe à " + premierJoueur.getScore());

                /*On met à jour le nb de coeur du gagnant*/
                coeurParPersonne[premierJoueur.getPosition()] = nbCoeurAuCentre;

                /*Afficher le classement*/
                GestionAffichage.afficherClassement(Humain, Ordi1, Ordi2, Ordi3);
                if(enregistrement) {
                    /*Enregistrer les scores*/
                }

                Thread.sleep(2000);
                System.out.println("\n");
                /*On affiche le joueur qui entame le prochain Pli si ce n'est pas le dernier*/
                if (numPli != 13) {
                    System.out.println("*" + premierJoueur.getNom()
                            + "* entame le Pli.\n");
                }

                if(enregistrement) {
                    /*Enregistrer le Joueur Gagnant*/
                }


            }
            numManche ++;

            /*On vérifie si quelqu'un à réalisé un grand Chelem*/
            for (int l = 0; l < coeurParPersonne.length; l ++) {
                if(coeurParPersonne[l] == 14) {
                    System.out.println(ordreDePassage[l] + " a réalisé un Grand Chelem !");
                    Humain.ajouterScore(26);
                    Ordi1.ajouterScore(26);
                    Ordi2.ajouterScore(26);
                    Ordi3.ajouterScore(26);
                    ordreDePassage[l].ajouterScore(-26 * 2);
                    break;
                }
            }

            for (int indexJoueur = 0; indexJoueur < 4; indexJoueur ++) {
                if (ordreDePassage[indexJoueur].getScore() >= 100) {
                    joueurPerdant = ordreDePassage[indexJoueur];
                    finDePartie = true;
                }
            }
        }

        System.out.println("Le Joueur " + joueurPerdant.getNom() + " a perdu !");
        GestionAffichage.afficherClassement(Humain, Ordi1, Ordi2, Ordi3);

        System.out.println("\nFin de la partie.");
        Thread.sleep(4000);
        ClassePrincipale.choixMenu();
    }

    /**
     * TODO Commenter le rôle de cette méthode (Commande ou requète ?)
     * @throws InterruptedException
     * @throws IOException
     */
    public static void lancerDistribution() throws InterruptedException, IOException {

        Scanner clavier = new Scanner(System.in);

        /* compte le nb de coeur de chaque personnnes*/
        int[] coeurParPersonne = new int[4];

        String nomFichier;
        String nomFichierTxt;
        String nomFichierBin;
        int numManche;
        int joueurEnCour;
        int nbCoeurAuCentre;
        boolean coeurTombe;
        boolean saisieCorrect;
        Joueur premierJoueur; // le joueur qui commence à jouer, est compris entre 1 et 4

        String pseudo;

        pseudo = "Utilisateur"; // bouchon
        numManche = 0;

        System.out.println("Test Distribution\n");

        nomFichierBin = ""; //bouchon

        /*Création des 4 joueurs*/
        JoueurHumain Humain = new JoueurHumain(0, pseudo);
        JoueurOrdi Ordi1 = new JoueurOrdi(1, "Ordi_Est");
        JoueurOrdi Ordi2 = new JoueurOrdi(2, "Ordi_Nord");
        JoueurOrdi Ordi3 = new JoueurOrdi(3, "Ordi_Ouest");
        Joueur[] ordreDePassage = {Humain, Ordi1, Ordi2, Ordi3};


        /* On remet à 0 le compte des coeurs */
        for (int i = 0; i < coeurParPersonne.length; i ++) {
            coeurParPersonne[i] = 0;
        }

        /* On distribue les cartes */
        /* On demande le nom du fichier contenant les distribution */
        saisieCorrect = false;
        do {
            try {
                System.out.print("Veuillez saisir le nom du fichier distribution : ");
                nomFichier = clavier.next();
                clavier.nextLine();
                nomFichierBin = nomFichier + ".bin";
                GestionFichier.chargerDistribution(ordreDePassage, nomFichierBin);
                saisieCorrect = true;
            } catch (Exception e) {
                System.out.println("Le Fichier distribution n'a pas été trouvé."
                        + " Veuillez recommencer la saisie.");
            }
        } while (!saisieCorrect);

        GestionFichier.chargerDistribution(ordreDePassage , nomFichierBin);

        System.out.print("Veuillez choisir 3 cartes à échanger ");
        /*On Echange 3 carte avec les autres joueurs*/
        echangeCarte(numManche, Humain, Ordi1, Ordi2, Ordi3);
        premierJoueur = recherche2Trefles(ordreDePassage);


        System.out.println("\nEchange de Carte Terminé !");
        System.out.println(premierJoueur.getNom() + " possède le 2 de Trèfle."
                + " C'est donc à lui de commencer à jouer.\n\n");

        coeurTombe = false;
        for (int numPli = 1; numPli <= 13; numPli ++) {

            /*On enleve les cartes au centre de la table*/
            for (int indexCentre = 0; indexCentre <4; indexCentre ++) {
                centreTable[indexCentre] = new Carte(0);
            }

            /*Chaque joueur pose une carte*/
            joueurEnCour = premierJoueur.getPosition();
            nbCoeurAuCentre = 0;
            for(int i = 0; i < 4; i ++) {

                /* On vérifie si un coeur est déjà tombé */
                if (!coeurTombe) {
                    for (int indexCentre = 0; indexCentre < 4; indexCentre ++) {
                        if(centreTable[indexCentre].couleur().equals("Coeur")) {
                            coeurTombe = true;
                        }
                    }
                }

                /* Le joueur à qui c'est le tour pose une carte */
                joueurEnCour = joueurEnCour % 4;
                switch (joueurEnCour) {
                    case 0 -> {
                        /*Afficher le centre*/
                        GestionAffichage.afficherCentre(centreTable, premierJoueur.getPosition());

                        /*Afficher la main*/
                        GestionAffichage.afficherMainJoueur(Humain.mainJoueur());
                        centreTable[joueurEnCour] = Humain.carteAPoser(centreTable, numPli, coeurTombe,premierJoueur.getPosition());
                    }
                    case 1 -> {
                        centreTable[joueurEnCour] =
                                Ordi1.choisirCarteAPoser(centreTable, numPli, coeurTombe,premierJoueur.getPosition());
                    }
                    case 2 -> {
                        centreTable[joueurEnCour] = Ordi2.choisirCarteAPoser(centreTable, numPli, coeurTombe,premierJoueur.getPosition());
                    }
                    case 3 -> {
                        centreTable[joueurEnCour] = Ordi3.choisirCarteAPoser(centreTable, numPli, coeurTombe,premierJoueur.getPosition());
                    }
                }

                if (centreTable[joueurEnCour].couleur().equals("Coeur")
                        || centreTable[joueurEnCour].getNumCarte() == 412) {

                    nbCoeurAuCentre ++;
                }

                joueurEnCour ++;

            }



            /*Afficher le centre*/
            GestionAffichage.afficherCentre(centreTable, premierJoueur.getPosition());

            /*On vérifie qui gagne le pli*/
            premierJoueur = quiGagneLePli(centreTable, premierJoueur.getPosition(), ordreDePassage);
            System.out.println("\n" + premierJoueur.getNom() + " remporte le pli ! "
                    + "Son score  passe à " + premierJoueur.getScore());

            /*On met à jour le nb de coeur du gagnant*/
            coeurParPersonne[premierJoueur.getPosition()] = nbCoeurAuCentre;


            /*On affiche le joueur qui entame le prochain Pli si ce n'est pas le dernier*/
            if (numPli != 13) {
                /*Afficher le classement*/
                GestionAffichage.afficherClassement(Humain, Ordi1, Ordi2, Ordi3);

                Thread.sleep(2000);
                System.out.println("\n");

                System.out.println(premierJoueur.getNom()
                        + " entame le Pli.\n");
            }


        }

        /*On vérifie si quelqu'un à réalisé un grand Chelem*/
        for (int l = 0; l < coeurParPersonne.length; l ++) {
            if(coeurParPersonne[l] == 14) {
                System.out.println(ordreDePassage[l] + " a réalisé un Grand Chelem !");
                Humain.ajouterScore(26);
                Ordi1.ajouterScore(26);
                Ordi2.ajouterScore(26);
                Ordi3.ajouterScore(26);
                ordreDePassage[l].ajouterScore(-26 * 2);
                break;
            }
        }

        /*Afficher le classement*/
        GestionAffichage.afficherClassement(Humain, Ordi1, Ordi2, Ordi3);


        Thread.sleep(4000);

    }

    /**
     * Gere les echanges de cartes entre joueurs.
     * les echanges se font :
     * manche 0 : sens horraire
     * manche 1 : sens anti horraire
     * manche 2 : face à face
     * manche 3 : pas d'échange
     * manche 4 : on recommence
     * @param numManche : le numéro de la manche
     * @param Sud
     * @param Est
     * @param Nord
     * @param Ouest
     *
     */
    public static void echangeCarte(int numManche, JoueurHumain Sud, JoueurOrdi Est, JoueurOrdi Nord, JoueurOrdi Ouest) {

        Carte newNord[] = new Carte[3];
        Carte newSud[] = new Carte[3];
        Carte newEst[] = new Carte[3];
        Carte newOuest[] = new Carte[3];

        int manche;

        manche = numManche % 4;

        switch (manche) {
            case 0 -> {
                // sens horraire
                System.out.println("à " + Est.getNom() + " : ");

                for(int i = 0; i < 3; i ++) {

                    newOuest[i] = Sud.carteAEchanger();        //Sud
                    Sud.enleverCarte(newOuest[i]);
                    newNord[i] = Ouest.choisirCarteAEchanger(numManche); //Ouest
                    Ouest.enleverCarte(newNord[i]);
                    newEst[i] = Nord.choisirCarteAEchanger(numManche);  //Nord
                    Nord.enleverCarte(newEst[i]);
                    newSud[i] = Est.choisirCarteAEchanger(numManche);  //Est
                    Est.enleverCarte(newSud[i]);


                }
                System.out.println("\nVous donnez les cartes "
                        + newOuest[0].toString()
                        + ", " + newOuest[1].toString()
                        + ", " + newOuest[2].toString() + " à Ordi_Ouest");

            }
            case 1 -> {
                // sens anti-horraire
                System.out.println("à " + Ouest.getNom() + " : ");

                for(int i = 0; i < 3; i ++) {

                    newEst[i] = Sud.carteAEchanger();        //Sud
                    Sud.enleverCarte(newEst[i]);
                    newNord[i] = Est.choisirCarteAEchanger(numManche);  //Est
                    Est.enleverCarte(newNord[i]);
                    newOuest[i] = Nord.choisirCarteAEchanger(numManche);  //Nord
                    Nord.enleverCarte(newOuest[i]);
                    newSud[i] = Ouest.choisirCarteAEchanger(numManche); //Ouest
                    Ouest.enleverCarte(newSud[i]);


                }
                System.out.println("\nVous donnez les cartes "
                        + newEst[0].toString()
                        + ", " + newEst[1].toString()
                        + ", " + newEst[2].toString() + " à Ordi_Est");
            }
            case 2 -> {
                // face à face
                System.out.println("à " + Nord.getNom() + " : ");

                for(int i = 0; i < 3; i ++) {

                    newNord[i] = Sud.carteAEchanger();        //Sud
                    Sud.enleverCarte(newNord[i]);
                    newSud[i] = Nord.choisirCarteAEchanger(numManche);  //Nord
                    Nord.enleverCarte(newSud[i]);
                    newOuest[i] = Est.choisirCarteAEchanger(numManche);  //Est
                    Est.enleverCarte(newOuest[i]);
                    newEst[i] = Ouest.choisirCarteAEchanger(numManche); //Ouest
                    Ouest.enleverCarte(newEst[i]);


                }
                System.out.println("\nVous donnez les cartes "
                        + newNord[0].toString()
                        + ", " + newNord[1].toString()
                        + ", " + newNord[2].toString() + " à Ordi_Nord");
            }
            case 3 -> {
                // rien ne se passe
            }
        }

        if (manche != 3) {
            for(int i = 0; i < 3; i ++) {

                Nord.ajouterCarte(newNord[i]);
                Sud.ajouterCarte(newSud[i]);
                Ouest.ajouterCarte(newOuest[i]);
                Est.ajouterCarte(newEst[i]);

            }
            System.out.println("Vous recevez les cartes "
                    + newSud[0].toString()
                    + ", " + newSud[1].toString()
                    + ", " + newSud[2].toString() + ".");
        }
    }

    /**
     * Prends en arguments les 4 cartes au centres et vérifie qui à gagner le pli.
     * Met le score à jour en fonction du gagnant.
     * @param carteCentre : un tableau de 4 cartes sous cette forme :
     *                      case 0 ---> Sud
     *                      case 1 ---> Est
     *                      case 2 ---> Nord
     *                      case 3 ---> Ouest
     *
     * @param positionStarter : le joueur qui à commencé à jouer
     * @param lesJoueurs : Tous les joueurs de la partie
     * @return joueurGagnant : le joueur qui gagne le pli
     */
    public static Joueur quiGagneLePli(Carte[] carteCentre, int positionStarter, Joueur[] lesJoueurs) {

        Carte carteReference;
        int posJoueurGagnant;

        posJoueurGagnant = positionStarter;
        carteReference = carteCentre[positionStarter];

        /* on compare la carte du joueur i à la carte de référence */
        for(int i = 0; i < 4; i ++) {

            // on vérifie si la carte à comparer est de la bonne couleur
            if(carteReference.couleur().equals(carteCentre[i].couleur())) {

                // on vérifie si sa valeur est supérieur à la carte de référence
                if (carteReference.getNumCarte() % 100 <= carteCentre[i].getNumCarte() % 100) {
                    // cette carte est plus forte que l'ancienne
                    carteReference = carteCentre[i];
                    posJoueurGagnant = i;
                }
            }
        }

        /* on comptabilise les coeurs, et la dame de pique */
        for(int i = 0; i < 4; i ++) {

            if(carteCentre[i].couleur().equals("Coeur")) {
                lesJoueurs[posJoueurGagnant].ajouterScore(1);
            } else if (carteCentre[i].getNumCarte() == 412) {
                lesJoueurs[posJoueurGagnant].ajouterScore(13);
            }

        }

        return lesJoueurs[posJoueurGagnant];

    }

    /**
     * Recherche dans les mains des joueurs le 2 de trèfle
     * @param lesJoueurs : un tableau contenant tous les joueurs du jeu
     * @return la position du Joueur qui à le 2 de trèfle
     */
    public static Joueur recherche2Trefles(Joueur[] lesJoueurs) {

        for(int i = 0; i < 4; i ++) {
            if (lesJoueurs[i].rechercherCarte(new Carte(102))) {
                return lesJoueurs[i];
            }
        }
        throw new RuntimeException("La carte 2 de trèfle n'a pas été trouvée");


    }
}