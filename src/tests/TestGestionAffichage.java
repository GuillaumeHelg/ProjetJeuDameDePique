/*
 * TestGestionAffichage.java                                          20/04/2021
 * IUT Rodez, aucun droit d'auteur
 */
package jeu.dameDePique.tests;

import jeu.dameDePique.*;

/**
 *
 * @author Corentin DEPRECQ
 */
public class TestGestionAffichage {

    /** Exemple de main possible pour un joueur */
    private static final Carte[][] MAIN_JOUEUR = {

            /* Main de joueur complète */
            {new Carte(205), new Carte(113),
                new Carte(306), new Carte(410),
                new Carte(105), new Carte(212),
                new Carte(402), new Carte(306),
                new Carte(203), new Carte(112),
                new Carte(314), new Carte(406),
                new Carte(208)},

            /* Main de joueur incomplète */
            {new Carte(105), new Carte(413),
                    new Carte(106), new Carte(210),
                    new Carte(103), new Carte(212),
                    new Carte(202), new Carte(106),
                    new Carte(203), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0)},

            /* Main de joueur vide */
            {new Carte(0), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0)},

            /* Main de joueur avec une carte */
            {new Carte(106), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0), new Carte(0),
                    new Carte(0)}

    };

    /* Jeu de test de centre de table possible durant une partie */
    private static final Carte[][] CENTRE_CARTE = {

            /* Centre de table vide*/
            {new Carte(0), new Carte(0),
             new Carte(0), new Carte(0)},

            /* Centre de table complet */
            {new Carte(112), new Carte(403),
             new Carte(314), new Carte(208)},

            /* Centre de table incomplet */
            {new Carte(0), new Carte(403),
             new Carte(304), new Carte(0)},

            /* Centre de table incomplet */
            {new Carte(0), new Carte(403),
             new Carte(304), new Carte(0)},

            /* Centre de table incomplet */
            {new Carte(0), new Carte(403),
             new Carte(304), new Carte(406)}
    };

    /**
     * Test de la méthode afficherMenuPrincipale
     */
    public static void testAfficherMenuPrincipale() {

        System.out.println("\nTest de la méthode afficherMenuPrincipale (test visuel): \n");
        GestionAffichage.afficherMenuPrincipal();
    }


    /**
     * Test de la méthode afficherMainJoueur et afficherCartePossible
     */
    public static void testAfficherMainJoueur() {

        System.out.println("\n\nTest de la méthode afficherMainJoueur et de la méthode afficherCartePossible (test visuel): \n");

        for (Carte[] cartes : MAIN_JOUEUR) {
            GestionAffichage.afficherMainJoueur(cartes);
        }
    }

    /**
     * Test de la méthode afficherCentre
     */
    public static void testAfficherCentre() {

        System.out.println("\n\nTest de la méthode afficherCentre (test visuel): \n");

        for (int i = 0; i < 4; i++) {
            GestionAffichage.afficherCentre(CENTRE_CARTE[i], 0);
        }

    }

    /**
     * Test de la méthode afficherClassement
     */
    public static void testAfficherClassement() {

        System.out.println("\n\nTest de la méthode afficherClassement (test visuel):");

        /* Exemple de score possible pour les joueurs*/
        JoueurHumain j1 = new JoueurHumain(2, "Corentin");
        JoueurOrdi j2 = new JoueurOrdi(1, "Lucas");
        JoueurOrdi j3 = new JoueurOrdi(0, "Ludovic");
        JoueurOrdi j4 = new JoueurOrdi(3, "Jades");

        /* Touts les joueurs ont un score différent */
        System.out.println("Test de l'affichage de score différent : ");
        j1.ajouterScore(1);
        j2.ajouterScore(2);
        j3.ajouterScore(3);
        j4.ajouterScore(4);

        GestionAffichage.afficherClassement(j1, j2, j3, j4);

        /* Tout les joueurs ont le même score */
        System.out.println("\nTest de l'affichage de score même valeur : ");
        j1.ajouterScore(4);
        j2.ajouterScore(3);
        j3.ajouterScore(2);
        j4.ajouterScore(1);

        GestionAffichage.afficherClassement(j1, j2, j3, j4);

        /* Deux joueurs ont le même score */
        System.out.println("\nTest de l'affichage de  2 scores même valeur : ");
        j1.ajouterScore(4);
        j2.ajouterScore(1);
        j3.ajouterScore(4);
        j4.ajouterScore(2);

        GestionAffichage.afficherClassement(j1, j2, j3, j4);
    }

    /**
     * Test de la méthode afficherMenuDebug
     */
    public static void testAfficherMenuDebug() {

        System.out.println("\n\nTest de la méthode afficherMenuDebug (test visuel): \n");
        GestionAffichage.afficherMenuDebug();
    }



    /**
     * Lancement des méthodes de tests
     */
    public static void main(String[] args) {

        testAfficherMenuPrincipale();
        testAfficherMainJoueur();
        testAfficherCentre();
        testAfficherClassement();
        testAfficherMenuDebug();

    }
}
