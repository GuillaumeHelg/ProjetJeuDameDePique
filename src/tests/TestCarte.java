/*
 * TestDate.java                            23/04/2021
 * IUT Rodez, Aucun droit d'auteur
 */
package jeu.dameDePique.tests;

import jeu.dameDePique.Carte;

import java.util.Arrays;

/**
 * Test de classe Carte
 * @author Corentin DEPRECQ
 */

public class TestCarte {

    /** Jeux de test de cartes valides */
    private static final Carte[] CARTES_VALIDES = {
        new Carte(112),
        new Carte(302),
        new Carte(209),
        new Carte(414)
    };

    /**
     * Test de Carte
     */
    public static void testCarteInt() {

        /* Jeu de test de numéro de carte invalide */
        final int[] NUM_CARTE_INVALIDE = {
            -10, 0, 45, 101, 165, 200, 215, 367, 1222
        };

        for (int invalide : NUM_CARTE_INVALIDE) {
            try {
                new Carte(invalide);
                throw new IllegalArgumentException("Echec de testCarteInt");
            } catch (IllegalArgumentException asc) {
                /* Test OK */
            }
        }
    }

    /**
     * Test de la méthode getNumCarte
     */
    public static void testGetNumCarte() {

        final int[] NUM_ATTENDU = {
                112, 302, 209, 414
        };

        for (int i = 0; i < NUM_ATTENDU.length; i++) {

            if (NUM_ATTENDU[i] != CARTES_VALIDES[i].getNumCarte()) {
                throw new IllegalArgumentException("Echec de testGetNumCarte");
            }
        }
    }

    /**
     * Test de la méthode valeur
     */
    public static void testValeur() {

        final String[] VALEUR_ATTENDU = {
            "Dame",
            "2",
            "9",
            "As"
        };

        for (int i = 0; i < VALEUR_ATTENDU.length; i++) {

            if (!VALEUR_ATTENDU[i].equals(CARTES_VALIDES[i].valeur())) {
                throw new IllegalArgumentException("Échec de testValeur");
            }
        }
    }

    /**
     * Test de la méthode couleur
     */
    public static void testCouleur() {

        final String[] COULEUR_ATTENDU = {
                "Trèfle",
                "Coeur",
                "Carreau",
                "Pique"
        };

        for (int i = 0; i < COULEUR_ATTENDU.length; i++) {

            if (!COULEUR_ATTENDU[i].equals(CARTES_VALIDES[i].couleur())) {
                throw new IllegalArgumentException("Échec de testCouleur");
            }
        }
    }

    /**
     * Test de la méthode toString
     */
    public static void testToString() {

        final String[] CONVERSION = {
            "Dame de Trèfle",
            "2 de Coeur",
            "9 de Carreau",
            "As de Pique"
        };

        for (int i = 0; i < CONVERSION.length; i++) {

            if (!CONVERSION[i].equals(CARTES_VALIDES[i].toString())) {
                throw new IllegalArgumentException("Échec de testToString");
            }
        }
    }

    /**
     * Lancement des méthodes de tests
     * @param args non utilisé
     */
    public static void main(String[] args) {

        /* Test de la méthode Carte */
        try {
            testCarteInt();
            System.out.println("testCarteInt réussi");
        } catch (IllegalArgumentException asc) {
            System.out.println("Echec de testCarteInt");
        }



        /* Test de la méthode getNumCarte */
        try {
            testGetNumCarte();
            System.out.println("testGetNumCarte réussi");
        } catch (IllegalArgumentException ide) {
            System.out.println("Echec de testGetNumCarte");
        }

        /* Test de la méthode valeur */
        try {
            testValeur();
            System.out.println("testValeur réussi");
        } catch (IllegalArgumentException ide) {
            System.out.println("Echec de testValeur");
        }

        /* Test de la méthode valeur */
        try {
            testCouleur();
            System.out.println("testCouleur réussi");
        } catch (IllegalArgumentException ide) {
            System.out.println("Echec de testCouleur");
        }

        /* Test de la méthode toString */
        try {
            testToString();
            System.out.println("testToString réussi");
        } catch (IllegalArgumentException ide) {
            System.out.println("Echec de testToString");
        }
    }
}
