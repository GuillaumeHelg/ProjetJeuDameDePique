/* TestGestionDistribution.java                                              23 avr. 2021
 *
 * IUT Rodez, aucun droit d'auteur
 */

package jeu.dameDePique.tests;
import java.util.Arrays;

import jeu.dameDePique.*;

/**
 * S'occupe des tests pour la classe GestionDistribution
 * @author Romain
 *
 */
public class TestGestionDistribution {

    private final static int[] PAQUET_CARTES = {
            102,103,104,105,106,107,108,109,110,111,112,113,114, //Trefles
            202,203,204,205,206,207,208,209,210,211,212,213,214, //Carreaux
            302,303,304,305,306,307,308,309,310,311,312,313,314, // Coeurs
            402,403,404,405,406,407,408,409,410,411,412,413,414 // Piques
    };

    private static Carte cartesObtenueLorsDeCeTirage[] = new Carte[13];

    /**
     * Test la méthode DistribuerCartes() : 
     * Test visuel : on vérifie que tous les joueurs ont des cartes dans leur main
     */
    public static void TestDistribuerCartes() {


        // création de 4 joueur;
        Joueur Joueur1 = new Joueur(0, "Luigi");
        Joueur Joueur2 = new Joueur(1, "Ordi_Est");
        Joueur Joueur3 = new Joueur(2, "Ordi_Nord");
        Joueur Joueur4 = new Joueur(3, "Ordi_Ouest");

        // on distribue les cartes
        GestionDistribution.distribuerCartes(Joueur1, Joueur2, Joueur3, Joueur4);
        
        /* on vérifie visuellement si les cartes
        sont bien apparues dans les mains des joueurs*/
        System.out.println(Arrays.toString(Joueur1.mainJoueur()));
        System.out.println(Arrays.toString(Joueur2.mainJoueur()));
        System.out.println(Arrays.toString(Joueur3.mainJoueur()));
        System.out.println(Arrays.toString(Joueur4.mainJoueur()));

    }


    /**
     * affecte un tableau aléatoire au tableau cartesObtenueLorsDeCeTirage
     * @param cartes
     */
    public static void AllouerTableau(Carte[] cartes) {
        for(int i = 0; i < cartesObtenueLorsDeCeTirage.length; i ++) {
            cartesObtenueLorsDeCeTirage[i] = cartes[i];
        }
    }

    /**
     * Test la méthode GenererMainAléatoire : 
     * vérifie si toutes les cartes ont toutes été distribuée
     * et qu'aucune ne soit en double.
     */
    public static void TestGenererMainAléatoire() {

        Carte cartesObtenue[] = new Carte[52];

        /*On affecte au tableau cartesRestantes les valeurs de toutes les cartes du jeu*/
        for(int i = 0; i < GestionDistribution.cartesRestantes.length; i ++) {
            GestionDistribution.cartesRestantes[i] = PAQUET_CARTES[i];
        }

        /* On tire 4 mains aléatoirement et on les places dans un tableau */
        for(int j = 0; j < 4; j++) {
            System.out.println("cartes Restantes dans le paquet"
                    + Arrays.toString(GestionDistribution.cartesRestantes));
            AllouerTableau(GestionDistribution.genererMainAleatoire());
            System.out.println("main n°" + j + " : "
                    + Arrays.toString(cartesObtenueLorsDeCeTirage) + "\n");
            for(int i = 0; i < 13; i ++) {
                cartesObtenue[j* 13 + i] = cartesObtenueLorsDeCeTirage[i];
            }
        }

        /* On trie par ordre croissant le tableau obtenu au for précédent*/
        /* Algorytme de trie */
        for (int i = 0; i < cartesObtenue.length - 1; i++)
        {
            int index = i;
            Carte min;

            for (int j = i + 1; j < cartesObtenue.length; j++)
            {
                if (cartesObtenue[j].getNumCarte() < cartesObtenue[index].getNumCarte()){
                    index = j;
                }
            }

            min = cartesObtenue[index];
            cartesObtenue[index] = cartesObtenue[i];
            cartesObtenue[i] = min;
        }

        /* On vérifie que toutes les cartes ont bien été tirées
         * en comparant le tableau obtenu au paquet initial */
        int compteurDeRéussite;
        compteurDeRéussite = 0;
        for(int i = 0; i < 52; i ++) {
            if(cartesObtenue[i].getNumCarte() == PAQUET_CARTES[i]) {
                compteurDeRéussite ++;
            }
        }

        if (compteurDeRéussite == 52) {
            System.out.println("Test Réussi : toutes les cartes ont été distribué"
                    + " et aucune n'est en double.");
        } else {
            System.err.println("Test Echoué");
        }

    }

    /**
     * Appelle les différentes méthodes de tests
     * @param args pas d'arguments
     */
    public static void main(String[] args) {
        TestGenererMainAléatoire();
        TestDistribuerCartes();
    }

}