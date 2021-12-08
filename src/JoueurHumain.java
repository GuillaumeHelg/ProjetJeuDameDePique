/*
 * JoueurHumain.java                                     6 mai 2021
 * Aucun droit d'auteur
 */
package jeu.dameDePique;

import java.util.Scanner;

/**
 * Permet une saisie valide d'une carte selon la main du joueur et du centre
 * de table
 * <ul>
 *     <li>Choisir une carte a poser sur la table</li>
 *     <li>Choisir une carte a échanger</li>
 * </ul>
 * @author Corentin DEPRECQ
 */

public class JoueurHumain extends Joueur {

    /* Entrée standard clavier */
    private static final Scanner CLAVIER = new Scanner(System.in);

    /**
     * Nouvel objet joueur
     *
     * @param position fournit la position du joueur parmi les 4 positions
     *                 possibles :
     *                 <ul>
     *                      <li>0 -> Sud</li>
     *                      <li>1 -> Est</li>
     *                      <li>2 -> Nord</li>
     *                      <li>3 -> Ouest</li>
     *                 </ul>
     * @param nom      fournit le pseudo du joueur
     */
    public JoueurHumain(int position, String nom) {
        super(position, nom);
    }


    /**
     * Effectue la saisie d'une carte a poser sur la table
     * @param coeurTombe true si un coeur est déjà tombé dans la manche en cour
     * @param centreTable tableau avec les cartes déjà posées
     * @param numPlis numéro du plis dans la manche
     * @param positionPremierJoueur position du joueur qui a posé une carte
     *                              en premier
     * @return Carte choisie
     */
    public Carte carteAPoser(Carte[] centreTable, int numPlis, boolean coeurTombe, int positionPremierJoueur) {

        /* Construction d'un tableau de carte de possible */
        Carte[] cartePossible;

        cartePossible = cartePossible(centreTable, numPlis, coeurTombe, positionPremierJoueur);

        /* Affichage des cartes possible a échanger du joueur */
        GestionAffichage.afficherCartePossible(cartePossible);

        Carte carteARetourner = new Carte(choisieCarte(cartePossible).getNumCarte());
        this.enleverCarte(carteARetourner);
        return carteARetourner;
    }

    /**
     * Effectue la saisie d'une carte a échanger
     * @return la carte choisie pour l'échange
     */
    public Carte carteAEchanger() {

        /* Affichage des cartes possible a échanger du joueur */
        GestionAffichage.afficherMainJoueur(super.mainJoueur());

        return choisieCarte(super.mainJoueur());
    }

    /**
     * Effectue une saisie d'une carte dans un tableau de carte possible à jouer
     * @param cartePossible tableau de carte possible a choisir
     * @return la carte que le joueur a choisie
     */
    public static Carte choisieCarte(Carte[] cartePossible) {



        /* Saisie de la carte que l'on veut choisir */

        // Nom de la carte saisie par l'utilisateur
        String choix;

        // Carte choisi par l'utilisateur initialisé a 0
        Carte carteChoisi = new Carte(0);


        /* On fait la saisie de la carte a poser */
        do {
            System.out.print("Choisir une carte parmi votre jeu : ");
            choix = CLAVIER.nextLine();

            for (int i = 0; i < cartePossible.length; i++) {

                if (choix.equals(cartePossible[i].toString())
                        || choix.equals(Integer.toString(i + 1))) {
                    carteChoisi = cartePossible[i];
                    break;
                }
            }

            if (carteChoisi.getNumCarte() == 0) {
                System.out.println("Erreur : Saisir une carte existante "
                        + "dans votre main !");
            } else {
                System.out.println("Vous avez choisi la carte : "
                        + carteChoisi + "\n");
            }

        } while (carteChoisi.getNumCarte() == 0);

        return carteChoisi;
    }
}