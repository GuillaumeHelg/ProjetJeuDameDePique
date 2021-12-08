/* ClassePrincipale.java                                              24 mai 2021
 *
 * IUT Rodez, aucun droit d'auteur, pas de copyright
 */

package jeu.dameDePique;
import java.io.IOException;
import java.util.Scanner;

/**
 * Contient les différents menu de démarrage du projet
 *
 * Cette classe permet de choisir si l'on souhaite  :
 *
 * <ul>
 *     <li>aller en mode debug</li>
 *      <ul>
 *          <li>Lancer une partie en mode normal avec enregistrement</li>
 *          <li>Lancer une nouvelle distribution</li>
 *          <li>Retourner au menu principal</li>
 *      </ul>
 * </ul>
 * <ul>
 *     <li>aller en mode normal</li>
 *      <ul>
 *          <li>Lancer une partie en mode normal sans enregistrement</li>
 *      </ul>
 * </ul>
 *
 * @author Guillaume
 * @version 1.0
 */
public class ClassePrincipale {


    /**
     * Dans la methode choixMenu :
     * <ul>
     *     <li>Lancer une partie sans enregistrement</li>
     *     <li>Lancer le menu debug</li>
     * </ul>
     *
     * @throws IOException : leve les exception d'interruption des programmes
     */
    public static void choixMenu() throws InterruptedException, IOException {

        Scanner clavier = new Scanner(System.in);


        int choix;
        boolean saisieOk;

        final int VALEUR_IMPOSSIBLE = -1; // Valeur impossible lors de la saisie

        GestionAffichage.afficherMenuPrincipal();


        /* permet de gerer si la saisie est eronnée ou si le choix est valide
         * les choix valide sont 1 ou 2 ou 3
         */
        do {
            System.out.print("\nSaisir le numéro du menu : ");
            choix = clavier.hasNextInt() ? clavier.nextInt()
                    : VALEUR_IMPOSSIBLE;

            saisieOk = choix > 0 && choix < 4;
            if (!saisieOk) {
                System.out.println("Votre valeur n'est pas un entier compris "
                        + "entre 1 et 2");
            }

            clavier.nextLine();
        } while (!saisieOk);


        /* permet de choisir quel menu l'on souhaite après être avoir lancer le programme
         * c'est le premier menu dans lequel l'on sera dirigé
         */
        switch (choix) {
            case 1 -> GestionPartie.lancerPartie(false);
            case 2 -> modeDebug();
            case 3 -> quitterLaPartie();
        }
    }

    /**
     * Ferme le Jeu
     */
    public static void quitterLaPartie() {

        System.out.println("\nMerci d'avoir jouer et à bientôt sur notre jeu !!\n");
        System.exit(0);
    }

    /**
     * La méthode du mode debug permet de  :
     * <ul>
     *     <li>Lancer une nouvelle distribution dans une fichier texte</li>
     *     <li>Lancer une partie avec enregistrement</li>
     *     <li>Retourner dans le mode normal si l'on regrette son choix</li>
     * </ul>
     *
     * @throws IOException : leve les exception d'interruption des programmes
     */
    public static void modeDebug() throws InterruptedException, IOException {


        Scanner clavier = new Scanner(System.in); //déclaration d'un scanner

        int choix; // contient le choix de l'utilisateur il contient soit 1 ou 2
        boolean saisieOk; // boolean qui permet de savoir si la saisie est valide

        final int VALEUR_IMPOSSIBLE = -1; // Valeur impossible lors de la saisie

        GestionAffichage.afficherMenuDebug();

        /* permet de gerer si la saisie est eronnée ou si le choix est valide
         * les choix valide sont 1 ou 2
         */
        do {
            System.out.print("\nSaisir le numéro du menu : ");
            choix = clavier.hasNextInt() ? clavier.nextInt()
                    : VALEUR_IMPOSSIBLE;

            saisieOk = choix > 0 && choix < 4;

            /* pas valide si l'utilisateur n'entre pas un entier */
            if (!saisieOk) {
                System.out.println("Votre valeur n'est pas un entier compris "
                        + "entre 1 et 2");
            }

            clavier.nextLine();
        } while (!saisieOk);

        /* permet de choisir quel menu l'on souhaite après être entré dans le menu debug */
        switch (choix) {
            case 1 -> GestionPartie.lancerPartie(true);
            case 2 -> GestionPartie.lancerDistribution();
            case 3 -> choixMenu();
        }
    }



    /**
     * Methode main qui invoque choixMenu au debut du programme
     * @param args non utilisé
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        choixMenu();
    }
}