/*
 * JoueurOrdi.java                          20/04/2021
 *
 * Iut de rodez, pas de copyright, pas de droit d'auteur
 */

package jeu.dameDePique;




/**
 * Classe JoueurOrdi, qui est utile au programme dame de pique
 *
 * @author guillaume Helg
 * @version 1.0
 */

public class JoueurOrdi extends Joueur {


    private final int TAILLE_MAIN = 13;


    /**
     * creation d'un objet Joueur Ordi qui représente une IA
     * @param position : c'est la position obtenue par le joueur ordi pour la partie
     * @param nom : c'est le nom de joueur ordi
     */
    public JoueurOrdi(int position, String nom)  {
        super(position, nom);
    }

    /**
     * methode qui permet de choisir la carte a posée en fonction de la main du Joueur
     * @param centreTable :
     * @param numPlis :
     * @param coeurDejaTombe :
     * @param positionPremierJoueur :
     * @return :
     */
    public Carte choisirCarteAPoser(Carte[] centreTable, int numPlis,
                                    boolean coeurDejaTombe, int positionPremierJoueur) {

        Carte[] cartePosable = new Carte[13];
        boolean coeurAuCentre;
        int plusFortCoeurAuCentre; // contient la valeur du plus fort coeur posé au centre
        int nbCarteNulleAuCentre;
        boolean immuniteADameDePique; /* si on ne possède ni as, roi et dame de pique
                                         alors on est immunisé a la dame de pique */

        /* contient la carte la plus forte possible de poser, par défaut contient la carte nulle */
        Carte carteLaPlusForte = new Carte(0);


        coeurAuCentre = false;
        plusFortCoeurAuCentre = 0; // bouchon

        /* on enregistre les carte possible de poser dans un tableau */
        for (int i = 0; i < TAILLE_MAIN; i++) {
            cartePosable[i] = super.cartePossible(centreTable, numPlis, coeurDejaTombe, positionPremierJoueur)[i];
        }

        /* Algorithme de trie du tableau de cartePosable  */
        for (int i = 0; i < cartePosable.length - 1; i++) {

            int index = i;
            Carte max;

            for (int j = i + 1; j < cartePosable.length; j++) {

                if (cartePosable[j].getNumCarte() > cartePosable[index].getNumCarte()) {
                    index = j;
                }
            }

            max = cartePosable[index];
            cartePosable[index] = cartePosable[i];
            cartePosable[i] = max;
        }


        /* on vérifie si y'a du coeur ou la dame de pique a deja été posé au centre de la table */
        for (Carte carte : centreTable) {
            if (carte.couleur().equals("Coeur") || carte.getNumCarte() == 412) {
                coeurAuCentre = true;
                if (carte.getNumCarte() % 100 > plusFortCoeurAuCentre) {
                    plusFortCoeurAuCentre = carte.getNumCarte() % 100;
                }
            }
        }

        /* si y'a du coeur au centre on pose une carte plus petite que le plus
         * fort coeur du centre afin de ne pas prendre de plis, si on ne possède
         * pas de carte plus petite on posera la carte la plus grosse pour s'en
         * débarrasser
         */
        if (coeurAuCentre) {
            for (int i = 0; i < TAILLE_MAIN; i++) {
                if (cartePosable[i].getNumCarte() % 100 < plusFortCoeurAuCentre && cartePosable[i].getNumCarte() != 0) {
                    for (int j = 0 ; j < TAILLE_MAIN ; j++) {
                        /* on vérifie que nous sommes obligé de prendre cette carte */
                        if (cartePosable[j].couleur().equals(centreTable[positionPremierJoueur].couleur())
                            && cartePosable[j].getNumCarte() % 100 < centreTable[positionPremierJoueur].getNumCarte() % 100) {
                            Carte carteARetourner = new Carte(cartePosable[j].getNumCarte());
                            this.enleverCarte(cartePosable[j]);
                            return carteARetourner;
                        }
                    }
                    Carte carteARetourner = new Carte(cartePosable[i].getNumCarte());
                    this.enleverCarte(cartePosable[i]);
                    return carteARetourner;
                }
            }


            for (int l = 0; l < TAILLE_MAIN; l++) {
                if (cartePosable[l].getNumCarte() % 100 > carteLaPlusForte.getNumCarte() % 100) {
                    carteLaPlusForte = cartePosable[l];
                }

            }
            Carte carteARetourner = new Carte(carteLaPlusForte.getNumCarte());
            this.enleverCarte(carteLaPlusForte);
            return carteARetourner;
        }

        /* Si nbCarteNulleAuCentre = 1 , cela signifie que l'on est le dernier joueur a poser une carte */
        nbCarteNulleAuCentre = 0; //initialisation
        for(Carte carteAuCentre : centreTable) {
            if (carteAuCentre.getNumCarte() == 0) {
                nbCarteNulleAuCentre++;
            }
        }

        carteLaPlusForte = cartePosable[0];
        for(int i = 0 ; i < TAILLE_MAIN ; i++) {
            if (cartePosable[i].getNumCarte() % 100 < carteLaPlusForte.getNumCarte() % 100
                    && cartePosable[i].getNumCarte() != 0) {
                carteLaPlusForte = cartePosable[i];
            }
        }

        /* Si on est le dernier joueur a jouer et qu'il n'y a pas de coeur,
           on essaie de poser notre carte la plus forte */
        if (nbCarteNulleAuCentre == 1) {
            boolean queDuCoeur; // true si on ne peut poser que du coeur


            /* on vérifie si l'on ne peut poser que du coeur */
            queDuCoeur = true;
            for( int i = 0 ; i < TAILLE_MAIN ; i++) {
                if(!(cartePosable[i].couleur().equals("Coeur") || cartePosable[i].getNumCarte() == 0)) {
                    queDuCoeur = false;
                }
            }

            /* Si on a que du coeur on pose la carte la plus forte */
            if (queDuCoeur) {
                for (int m = 0; m < TAILLE_MAIN; m++) {
                    if (cartePosable[m].getNumCarte() % 100 > carteLaPlusForte.getNumCarte() % 100) {
                        carteLaPlusForte = cartePosable[m];
                    }
                }
                Carte carteARetourner = new Carte(carteLaPlusForte.getNumCarte());
                this.enleverCarte(carteLaPlusForte);
                return carteARetourner;
            }

            /* Si on a pas que du coeur on pose la carte la plus forte qui n'est pas du coeur*/
            for (int l = 0; l < TAILLE_MAIN; l++) {
                if (cartePosable[l].getNumCarte() % 100 > carteLaPlusForte.getNumCarte() % 100) {
                    if (cartePosable[l].getNumCarte() != 412 && !(cartePosable[l].couleur().equals("Coeur"))) {
                        carteLaPlusForte = cartePosable[l];
                    }
                }
            }
            Carte carteARetourner = new Carte(carteLaPlusForte.getNumCarte());
            this.enleverCarte(carteLaPlusForte);
            return carteARetourner;
        }

        /* en debut de partie on se débarrasse de nos cartes les plus forte */
        if (numPlis <= 3) {
            for (int h = 0; h < TAILLE_MAIN; h++) {
                if (cartePosable[h].getNumCarte() % 100 > carteLaPlusForte.getNumCarte() % 100) {
                    if (cartePosable[h].getNumCarte() != 412 && !(cartePosable[h].couleur().equals("Coeur"))) {
                        carteLaPlusForte = cartePosable[h];
                    }
                }
            }
            Carte carteARetourner = new Carte(carteLaPlusForte.getNumCarte());
            this.enleverCarte(carteLaPlusForte);
            return carteARetourner;

        }

        /* vérifie si on est immunisé ou non a la dame de pique */
        immuniteADameDePique = false; // bouchon
        for(int g = 0 ; g < TAILLE_MAIN ; g++) {
            if (super.mainJoueur()[g].getNumCarte() >= 412) {
                immuniteADameDePique = true;
            }
        }

        /* si on est immunisé a la dame de pique on pose en priorité des piques */
        if (immuniteADameDePique) {
            for(int i = 0 ; i < TAILLE_MAIN ; i++) {
                if (cartePosable[i].couleur().equals("Pique")) {
                    Carte carteARetourner = new Carte(cartePosable[i].getNumCarte());
                    this.enleverCarte(cartePosable[i]);
                    return carteARetourner;
                }
            }
        }

        Carte carteARetourner = new Carte(cartePosable[0].getNumCarte());
        this.enleverCarte(cartePosable[0]);
        return carteARetourner;
    }


    /**
     * methode qui va permettre de choisir une carte a échanger en fonction de
     * la main du joueur et du numéro de la manche.
     * L' IA choisira automatiquement d' échanger la carte la plus forte en
     * évitant de donner au joueur situé a gauche la dame de pique. Cette
     * situation ne peut se produire que lorsque le numéro de la manche est
     * égal à 0.
     * @param numManche
     * @return :
     */
    public Carte choisirCarteAEchanger(int numManche) {

        Carte carteAEchanger;

        int manche;

        manche = numManche % 4;

        /* on attribut la 1ere carte de la main pour voir si les autres sont plus fortes ou non */
        if (super.mainJoueur()[0].getNumCarte() != 412 || manche != 0) {
            carteAEchanger = super.mainJoueur()[0];
        } else {
            carteAEchanger = super.mainJoueur()[1];
        }


        /* on analyse le reste de la main */
        for (int j = 1; j < TAILLE_MAIN; j++) {
            if (super.mainJoueur()[j].getNumCarte() % 100 // modulo 100
                    > carteAEchanger.getNumCarte() % 100) {
                if (super.mainJoueur()[j].getNumCarte() != 412 || manche != 0) {
                    carteAEchanger = super.mainJoueur()[j];
                }
            }
        }
        return carteAEchanger;
    }
}