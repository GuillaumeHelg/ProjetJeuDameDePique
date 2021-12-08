/*
 * TestJoueurHumain.java                                     14 mai 2021
 * Aucun droit d'auteur
 */

package jeu.dameDePique.tests;

import jeu.dameDePique.Carte;
import jeu.dameDePique.GestionAffichage;
import jeu.dameDePique.Joueur;
import jeu.dameDePique.JoueurHumain;

/**
 * Test de JoueurHumain
 */
public class TestJoueurHumain {

    private static final Carte[] CHOIX_CARTES_POSSIBLE1 = {
            new Carte(102),
            new Carte(305),
            new Carte(213),
            new Carte(412)

    };

    private static final Carte[] CHOIX_CARTES_POSSIBLE2 = {
            new Carte(112),
            new Carte(406),
            new Carte(403),
            new Carte(109)
    };

    private static final Carte[] CHOIX_CARTES_POSSIBLE3 = {
            new Carte(405),
            new Carte(112),
            new Carte(109),
            new Carte(213)
    };

    /**
     * Test de la méthode choisieCarte
     */
    public static void testChoisieCarte() {



        boolean saisieOk = false;

        System.out.println("Test de la méthode choisieCarte : \n");

        /* Test du premier jeu de cartes */
        GestionAffichage.afficherCartePossible(CHOIX_CARTES_POSSIBLE1);
        Carte carte1 = JoueurHumain.choisieCarte(CHOIX_CARTES_POSSIBLE1);

        for (Carte carte : CHOIX_CARTES_POSSIBLE1) {


            if (carte.getNumCarte() == carte1.getNumCarte()) {
                saisieOk = true;
                break;
            }
        }

        if (saisieOk) {
            System.out.println("Test n°1 : Réussi !\n");
        } else {
            System.out.println("Test n°1 : Echec !\n");
        }

        /* Test du second jeu de cartes */
        GestionAffichage.afficherCartePossible(CHOIX_CARTES_POSSIBLE2);

        Carte carte2 = JoueurHumain.choisieCarte(CHOIX_CARTES_POSSIBLE2);

        for (Carte carte : CHOIX_CARTES_POSSIBLE2) {


            if (carte.getNumCarte() == carte2.getNumCarte()) {
                saisieOk = true;
                break;
            } else {
                saisieOk = false;
            }
        }

        if (saisieOk) {
            System.out.println("Test n°2 : Réussi !\n");
        } else {
            System.out.println("Test n°2 : Echec !\n");
        }

        /* Test du troisième jeu de cartes */
        GestionAffichage.afficherCartePossible(CHOIX_CARTES_POSSIBLE3);

        Carte carte3 = JoueurHumain.choisieCarte(CHOIX_CARTES_POSSIBLE3);

        for (Carte carte : CHOIX_CARTES_POSSIBLE3) {


            if (carte.getNumCarte() == carte3.getNumCarte()) {
                saisieOk = true;
                break;
            } else {
                saisieOk = false;
            }
        }

        if (saisieOk) {
            System.out.println("Test n°3 : Réussi !\n");
        } else {
            System.out.println("Test n°3 : Echec !\n");
        }

    }

    /**
     * Lancement des méthodes de tests
     * @param args non utilisé
     */

    public static void main(String[] args) {

        /* lancement de la méthode de test de choisieCarte*/
        testChoisieCarte();
    }
}
