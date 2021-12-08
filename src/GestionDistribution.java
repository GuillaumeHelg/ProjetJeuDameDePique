/* GestionDistribution.java                                              22 avr. 2021
 *
 * IUT Rodez, aucun droit d'auteur
 */

package jeu.dameDePique;

/**
 * Contient toutes les méthodes nécessaire à la distribution des cartes
 * @author Romain
 */
public class GestionDistribution {

    private final static int[] PAQUET_CARTES = {
            102,103,104,105,106,107,108,109,110,111,112,113,114, //Trèfles
            202,203,204,205,206,207,208,209,210,211,212,213,214, //Carreaux
            302,303,304,305,306,307,308,309,310,311,312,313,314, // Coeurs
            402,403,404,405,406,407,408,409,410,411,412,413,414 // Piques
    };

//    //Pour la revue de projet, permet de distribuer une main prédéfinie/
//    private final static Carte[][]  DISTRIBUTION_PREDEFINIE= {
//
//            // Sud
//            {new Carte(102), new Carte(103), new Carte(104), new Carte(105),
//             new Carte(106),new Carte(107),new Carte(108),new Carte(109),
//             new Carte(110),new Carte(111),new Carte(112),new Carte(113),
//             new Carte(114) },
//
//               // Est
//            {new Carte(102), new Carte(103), new Carte(104), new Carte(105),
//             new Carte(106),new Carte(107),new Carte(108),new Carte(109),
//             new Carte(110),new Carte(111),new Carte(112),new Carte(113),
//             new Carte(114) },
//
//               // Nord
//            {new Carte(102), new Carte(103), new Carte(104), new Carte(105),
//             new Carte(106),new Carte(107),new Carte(108),new Carte(109),
//             new Carte(110),new Carte(111),new Carte(112),new Carte(113),
//             new Carte(114) },
//
//               // Ouest
//            {new Carte(102), new Carte(103), new Carte(104), new Carte(105),
//             new Carte(106),new Carte(107),new Carte(108),new Carte(109),
//             new Carte(110),new Carte(111),new Carte(112),new Carte(113),
//             new Carte(114) },
//    };


    public static int[] cartesRestantes = new int[52];

    /**
     * Distribue les cartes à tous les joueurs
     * @param Humain
     * @param Ordi1
     * @param Ordi2
     * @param Ordi3
     */
    public static void distribuerCartes(Joueur Humain, Joueur Ordi1, Joueur Ordi2, Joueur Ordi3) {

        /*On affecte au tableau cartesRestantes les valeurs de toutes les cartes
         * du jeu
         */
        for (int i = 0; i < cartesRestantes.length; i ++) {
            cartesRestantes[i] = PAQUET_CARTES[i];
        }
        Humain.creerMain(genererMainAleatoire());
        Ordi1.creerMain(genererMainAleatoire());
        Ordi2.creerMain(genererMainAleatoire());
        Ordi3.creerMain(genererMainAleatoire());

//        Permet de faire une distribution prédéfinie pour la revue de projet
//        Humain.creerMain(DISTRIBUTION_PREDEFINIE[0]);
//        Ordi1.creerMain(DISTRIBUTION_PREDEFINIE[1]);
//        Ordi2.creerMain(DISTRIBUTION_PREDEFINIE[2]);
//        Ordi3.creerMain(DISTRIBUTION_PREDEFINIE[3]);

    }

    /**
     * Génère un tableau de 13 int choisi dans le tableau en argument
     * @return un tableau de 13 int
     */
    public static Carte[] genererMainAleatoire() {

        Carte[] cartesTirees = new Carte[13]; // enregistre toutes les cartes tirées au hasard
        int nbRandom;
        int carteTiree;                 // la carte Tirée au hasard

        /*on répète jusqu'a ce qu'on ai tiré 13 cartes*/
        for(int i = 0; i < 13; i ++) {

            /*On choisi un nombre aléatoire entre 0 et 52*/
            do {
                nbRandom = ((int)(Math.random() * 1.0E9)) % (cartesRestantes.length);
                carteTiree = cartesRestantes[nbRandom];
            } while(carteTiree == 0);
            cartesRestantes[nbRandom] = 0;
            cartesTirees[i] = new Carte(carteTiree);
        }
        return cartesTirees;

    }
}