/*
 * GestionAffichage.java                                                  20/04/2021
 * IUT Rodez, aucun droit d'auteur
 */
package jeu.dameDePique;

/**
 * Classe qui gère les affichages de :
 * <ul>
 *     <li>Menu Principale de l'application</li>
 *     <li>Les cartes que le joueur a en main</li>
 *     <li>Les cartes que le joueur peut jouer</li>
 *     <li>Du centre de la Table</li>
 *     <li>Le classement des joueurs par rapport au score</li>
 *     <li>Le menu Debug</li>
 * </ul>
 * @author Corentin DEPRECQ
 */

public class GestionAffichage {

    /**
     * Affichage du menu Principale lors du lancement de l'application
     */
    public static void afficherMenuPrincipal() {

        System.out.println("\n==============DAME DE PIQUE==============\n\n");

        System.out.println("""
                     Bienvenue dans le jeu : DAME DE PIQUE\s

                La Dame de pique est un jeu de cartes se jouant
                 avec un jeu de 52 cartes. Il se joue à
                   quatre joueurs avec treize cartes chacun.

                """);
        System.out.println("""
                Taper 1 pour jouer une partie\s
                Taper 2 pour accéder au menu debug
                Taper 3 pour quitter le jeu""".indent(3));
    }

    /**
     * Affichage des cartes que le joueur a en main
     * @param tableJoueur tableau dans lequel il y a toutes les cartes du joueur
     */

   public static void afficherMainJoueur(Carte[] tableJoueur) {

        StringBuilder resultant;        // Afficher la main du joueur
        resultant = new StringBuilder();

        for (int i = 0; i < tableJoueur.length; i++) {

            if (tableJoueur[i] != null && tableJoueur[i].getNumCarte() != 0) {
                resultant.append("/").append(i + 1).append("\\ ").append(tableJoueur[i]).append(" | ");
            }
        }

        if (resultant.isEmpty()) {
            resultant.append("La main est vide !");
        }

        System.out.println("Votre jeu est actuellement constitué de :\n" + resultant + "\n");
   }

    /**
     * Affichage des cartes que le joueur peut joueur dans le tour
     * @param tableJoueur tableau dans lequel il y a toutes les cartes du joueur
     */

    public static void afficherCartePossible(Carte[] tableJoueur) {

        StringBuilder choixPossible;        // Afficher la main du joueur
        choixPossible = new StringBuilder();

        for (int i = 0; i < tableJoueur.length; i++) {

            if (tableJoueur[i] != null && tableJoueur[i].getNumCarte() != 0) {
                choixPossible.append("/").append(i + 1).append("\\ ").append(tableJoueur[i]).append(" | ");
            }
        }

        if (choixPossible.isEmpty()) {
            choixPossible.append("La main est vide !");
        }

        System.out.println("Vous pouvez actuellement jouer :\n" + choixPossible);
    }


    /**
     * Affichage du centre de la Table
     * position :
     * 0 = Sud
     * 1 = Est
     * 2 = Nord
     * 3 = Ouest
     * @param centreTable Centre de table c'est à dire la où sont posées les
     *                    cartes lors d'un plis
     * @param premierJoueur chiffre compris entre 0 et 4 qui informe sur la
     *                      position du joueur qui a posé la carte en premier
     */
    public static void afficherCentre(Carte[] centreTable, int premierJoueur) {

        /* Chaîne de caractère contenant le nom de carte du centre de la table
         * et si la carte n'existe pas (ou carte(0)) alors on affiche "?"
         */
        String[] carteCentre = new String[4];

        /* Attribution des chaîne de caractère en fonction de la présence de la
        * carte ou non
        */
        carteCentre[0] = centreTable[0].getNumCarte() != 0
                       ? centreTable[0].toString()
                       : "   ?   ";
        carteCentre[1] = centreTable[1].getNumCarte() != 0
                       ? centreTable[1].toString()
                       : "?           ";
        carteCentre[2] = centreTable[2].getNumCarte() != 0
                       ? centreTable[2].toString()
                       : "     ?";
        carteCentre[3] = centreTable[3].getNumCarte() != 0
                       ? centreTable[3].toString()
                       : "?     ";

        /* On affiche les "*" pour le premier joueur qui joue dans le plis */
        carteCentre[premierJoueur] = "* " + carteCentre[premierJoueur] + " *";




        /* Affichage du centre de la table */
        System.out.println("            " + carteCentre[2]);
        System.out.printf("\n\n%15s  %20s\n\n\n", carteCentre[3], carteCentre[1]);
        System.out.println("            " + carteCentre[0] + "\n");

    }


    /**
     * Affichage du classement des joueurs selon leurs points
     * @param j1 Joueur numéro 1
     * @param j2 Joueur numéro 2
     * @param j3 Joueur numéro 3
     * @param j4 Joueur numéro 4
    */

    public static void afficherClassement(Joueur j1, Joueur j2, Joueur j3, Joueur j4) {

    /* Initialisation du tableau avec les scores des quatre joueur */

    Joueur[] triScore = { j1, j2, j3, j4};

    /* Algorithme de tri du tableau de score */
    for (int i = 0; i < triScore.length - 1; i++) {

        int index = i;
        Joueur min;

        for (int j = i + 1; j < triScore.length; j++) {

            if (triScore[j].getScore() < triScore[index].getScore()){
                index = j;
            }
        }

        min = triScore[index];
        triScore[index] = triScore[i];
        triScore[i] = min;
    }


    /* Affichage des résultat du classement */
    for (int a = 0; a < triScore.length; a ++) {

        if (triScore[0].getScore() == triScore[1].getScore()
            && triScore[0].getScore() == triScore[2].getScore()
            && triScore[0].getScore() == triScore[3].getScore()) {

            System.out.println(j1.getNom() + ", " + j2.getNom() + ", "
                               + j3.getNom() + " et " + j4.getNom()
                               + " ont actuellement un même score de "
                               + j1.getScore());
            break;
        }
         else if (triScore[a] == j1) {
            System.out.println(j1.getNom() + " est actuellement le numéro "
                    + (a + 1) + " au classement de la partie avec "
                    + j1.getScore());
        } else if (triScore[a] == j2) {
            System.out.println(j2.getNom() + " est actuellement le numéro "
                    + (a + 1) + " au classement de la partie avec "
                    + j2.getScore());
        } else if (triScore[a] == j3) {
            System.out.println(j3.getNom() + " est actuellement le numéro "
                    + (a + 1) + " au classement de la partie avec "
                    + j3.getScore());
        } else if (triScore[a] == j4) {
            System.out.println(j4.getNom() + " est actuellement le numéro "
                    + (a + 1) + " au classement de la partie avec "
                    + j4.getScore());
        }
    }
}

    /**
     * Affichage du menu de la partie "debug"
     */

    public static void afficherMenuDebug() {

        System.out.println("\nVous êtes dans le mode debug :");
        System.out.println("""
                Taper 1 pour jouer une partie\s
                Taper 2 pour charger une distribution de carte pré-enregistrer
                Taper 3 pour revenir en arrière""".indent(3));
    }
}




