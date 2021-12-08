/*
 * Joueur.java                          20/04/2021
 * Iut de rodez, pas de copyright, pas de droit d'auteur
 */

package jeu.dameDePique;



/**
 * Classe Objet Joueur, qui est utile au programme dame de pique
 *
 * <ul>
 *     Dans celle-ci on va retrouver :
 *     <ul>
 *          <li>la position du joueur : les positions possible sont 0, 1, 2 ou 3</li>
 *          <li>le nom du joueur</li>
 *          <li>la main du joueur</li>
 *          <li>le score du joueur</li>
 *     </ul>
 * </ul>
 *
 * @author guillaume Helg
 * @version 1.0
 */

public class Joueur {

    /**
     * position minimale : de 0 , elle comprise entre 0 et 3
     */
    private static final int POSITION_MIN = 0;

    /**
     * position maximale : de 3 , elle comprise entre 0 et 3
     */
    private static final int POSITION_MAX = 3;

    /**
     * taille du nom maximale : 15, elle est comprise entre 4 et 15
     */
    private static final int TAILLE_NOM_MAX = 15;

    /**
     * taille du nom minimale : 4, elle est comprise entre 4 et 15
     */
    private static final int TAILLE_NOM_MIN = 4;

    /**
     * taille maximale de la main du joueur egale a 13
     */
    private static final int TAILLE_MAIN = 13;

    /**
     * contient la position du joueur dans la partie qui va de 0 a 3
     */
    private int position;

    /**
     * contient le nom du joueur dans la limite de 4 a 15 caractères
     */
    private String nom;

    /**
     * contient la main du joueur donc 13 carte
     */
    private Carte[] mainDuJoueur = new Carte[13];

    /**
     * contient le score du joueur
     */
    private int score;



    /**
     * <ul>
     * Creation d'un objet joueur qui est definit par :
     *      <li>sa postion : nord = 2, sud = 0, est = 1, ouest = 3</li>
     *      <li>son nom : compris entre 4 et 15 caractere, n'importe quel caractere</li>
     * </ul>
     * @param position fournit la position du joueur parmi les 4 positions possibles
     * @param nom fournit le pseudo du joueur
     */
    public Joueur(int position, String nom) {



        if (!(position == 0 || position == 1 || position == 2 || position == 3)) {
            throw new IllegalArgumentException("Erreur de saisie de la position qui " +
                    "doit �tre compris entre " + POSITION_MIN + " et " + POSITION_MAX +
                    ": " + toString());
        }
        this.position = position;

        if (nom.length() > TAILLE_NOM_MAX || nom.length() < TAILLE_NOM_MIN) {
            throw new IllegalArgumentException("Erreur de saisie de du nom qui " +
                    "doit �tre compris entre " + TAILLE_NOM_MIN + " et " +
                    TAILLE_NOM_MAX + " charact�res : " + toString());
        }
        this.nom = nom;

    }

    /**
     * créer une main a partir d'un tableau mainAEntrer et le mets dans un tableau mainJoueur
     * @param mainAEntrer : c'est la main qui est distribuée au joueur
     */
    public void creerMain(Carte[] mainAEntrer) {

        /* Si la main du joueur fait la meme taille que la mainAEntrer alors on copie */
        if (mainAEntrer.length == mainDuJoueur.length) {
            for (int i = 0; i < mainDuJoueur.length ; i++ ) {
                mainDuJoueur[i] = mainAEntrer[i];
            }
        } else {
            throw new IllegalArgumentException("Erreur : la main doit contenir 13 cartes");
        }
    }

    /**
     * ajoute dans la variable score le contenu de quantité
     * @param quantite : contient le score a ajouter
     * @return : retourne le score après ajout
     */
    public int ajouterScore(int quantite) {

        this.score += quantite;

        if (score < 0) {
            score = 0;
        }
        return score;
    }

    /**
     * ajoute dans la variable score le contenu de quantité
     * @param quantite : contient le score a ajouter
     * @return : retourne le score après ajout
     */
    public int modifScore(int quantite) {

        if (quantite < 0) {
            throw new IllegalArgumentException("Erreur : le score ne peut pas �tre n�gatif");
        }
        this.score = quantite;
        return score;
    }


    /**
     * enleve une carte dans la main du joueur qui est dans carteAEnlever
     * @param carteAEnlever : c'est la carte que l'on doit enlever de la main du joueur
     */
    public void enleverCarte(Carte carteAEnlever) {

        /* true si la carteAEnlever existe dans la main du joueur */
        boolean carteTrouve;
        carteTrouve = false;

        /* Analyse la main du joueur et verifie si la carte est dedans pour l'enlev�e */
        for(int k = 0; k < mainDuJoueur.length ; k++){
            if (mainDuJoueur[k].getNumCarte() == carteAEnlever.getNumCarte()) {
                mainDuJoueur[k] = new Carte(0);
                carteTrouve = true;
                break;

            }
        }
        /* le boolean reste sur false si la carte n'a pas �t� trouv� et provoque une
         *  RuntimeException */
        if (!carteTrouve){
            throw new RuntimeException("Erreur : la carte n'a pas �t� trouv�e dans la main ");
        }

    }

    /**
     * Cette méthode a pour but de faire un tableau de carte valide
     * <ul>
     *     <li>Si c'est le premier plis :</li>
     *     <ul>
     *         <li>Premier joueur a jouer il est obligé de jouer le 2 de
     *         Trèfle.</li>
     *         <li>Pour les autres joueurs ils doivent fournir la couleur
     *         demandée (la couleur de la première carte jouée) si ils ne
     *         possèdent aucune carte de la même couleur, ils peuvent jouer
     *         n'importe quelle cartes à l'exeption de la Dame de Pique et
     *         toutes cartes de coeur.</li>
     *     </ul>
     *     <li>Pour les autres plis :</li>
     *     <ul>
     *         <li>Le joueur qui commence a jouer le plis peu jouer toutes
     *             les cartes qu'il veut sauf si aucune carte de coeur
     *             n'a déjà été jouée dans ce cas il n'a pas le droit de
     *             commencer avec un carte de coeur.</li>
     *         <li>Il faut premièrement jouer une carte de
     *             la même couleur que la couleur de la première carte jouée.
     *         </li>
     *         <li>Si il n'en possède pas il peut jouer n'importe quelle autre
     *             carte dans la main active.</li>
     *     </ul>
     * </ul>
     *
     * @param centreTable cartes sur le plateau
     * @param numPlis le numéro de plis dans la partie
     * @param coeurTombe true si un coeur est déjà tombé dans la manche en cour
     * @param positionPremierJoueur position du premier joueur qui a posé la carte
     *                              au centre
     * @return un tableau de carte possible a jouer
     */
    public Carte[] cartePossible(Carte[] centreTable, int numPlis,
                                 boolean coeurTombe, int positionPremierJoueur) {

        /* True si le centre de la table est vide, c'est à dire aucune carte posée */
        final boolean CENTRE_VIDE = centreTable[0].getNumCarte() == 0
                && centreTable[1].getNumCarte() == 0
                && centreTable[2].getNumCarte() == 0
                && centreTable[3].getNumCarte() == 0;

        // Toutes les cartes possible de jouer
        Carte[] cartePossible = {
                new Carte(0), new Carte(0), new Carte(0), new Carte(0),
                new Carte(0),new Carte(0),new Carte(0),new Carte(0),
                new Carte(0),new Carte(0),new Carte(0),new Carte(0),
                new Carte(0)
        };


        Carte[] mainJoueur = this.mainDuJoueur; // Main du joueur

        int indiceDeCartePossible; // Indice qui s'incrémente à chaque fois qu'une carte est trouvée

        indiceDeCartePossible = 0;



        /* Si c'est le premier tour et premier joueur a poser une carte */
        if (numPlis == 1 && CENTRE_VIDE) {

            /* Il ne peut jouer que le 2 de Trèfle */
            cartePossible[0] = new Carte(102);
            return cartePossible;

        }

        /* On place toutes les cartes qui ont la même couleur que la première carte jouée */
        for (int i = 0; i < 13; i++) {

            if (mainJoueur[i].couleur().equals(centreTable[positionPremierJoueur].couleur())) {

                cartePossible[indiceDeCartePossible] = mainJoueur[i];
                indiceDeCartePossible ++;
            }
        }

        indiceDeCartePossible = 0;
        /* Si le joueur ne possède aucune carte de la même couleur de la première carte jouée */
        if (cartePossible[0].getNumCarte() == 0) {


            for (int i = 0; i < 13; i++) {

                /* Si c'est le premier plis */
                if (numPlis == 1) {

                    if (!mainJoueur[i].couleur().equals("Coeur") // Cartes différentes de coeur
                            && mainJoueur[i].getNumCarte() != 412) { // On peut pas jouer la dame de Pique au premier tour

                        cartePossible[indiceDeCartePossible] = mainJoueur[i];
                        indiceDeCartePossible ++;
                    }
                } else {  // Pour tout les autres plis donc > 1

                    /* Si c'est le premier joueur a jouer dans le pli et que
                     * aucun coeur n'a été jouer il ne peut commencer par jouer
                     * avec un coeur
                     */
                    if (CENTRE_VIDE && !coeurTombe) {

                        if (!mainJoueur[i].couleur().equals("Coeur")) { // Cartes différentes de coeur

                            cartePossible[indiceDeCartePossible] = mainJoueur[i];
                            indiceDeCartePossible ++;
                        }
                    } else {
                        /* il peut jouer toutes les cartes de sa main active */
                        cartePossible = mainJoueur;
                        break;
                    }
                }
            }
        }

        return cartePossible;
    }

    /**
     * Recherche la carte prise en paramètre dans la main du joueur
     * @param carteARecherche : prend en argument la carte que la méthode doit recherchée
     * @return true si la carte est bien présente dans la main du joueur
     */
    public boolean rechercherCarte (Carte carteARecherche) {

        boolean estPresent;

        estPresent = false;

        for(int i = 0 ; i < TAILLE_MAIN ; i++ ) {
            if(carteARecherche.getNumCarte() == mainDuJoueur[i].getNumCarte()) {
                estPresent = true;
                break;
            }
        }
        return estPresent;
    }

    /**
     * ajoute une carte dans la main du joueur a partir de carte a ajouter dans mainJoueur
     * @param carteAAjouter c'est la carte que l'on doit ajouter a la main du joueur
     */
    public void ajouterCarte(Carte carteAAjouter) {

        /* True si on peut ajouter la carte dans la main du joueur */
        boolean estAjoute;
        estAjoute = false;

        // on verifie que la carte n'est pas deja dans la main du joueur
        for (Carte carte : mainDuJoueur) {
            if (carteAAjouter.getNumCarte() == carte.getNumCarte()) {
                throw new RuntimeException("La carte existe deja dans la main");
            }
        }
        /* on ajoute la carte dans la main du joueur */
        for(int z = 0; z < mainDuJoueur.length ; z++) {
            if (mainDuJoueur[z].getNumCarte() == 0 ) {
                mainDuJoueur[z] = carteAAjouter;
                estAjoute = true;
                break;
            }
        }
        if(!estAjoute) {
            throw new RuntimeException("La carte n'a pas pu etre ajout�e");
        }
    }



    /** @return le score du joueur */
    public int getScore(){
        return score;
    }

    /** @return le nom de joueur */
    public String getNom() {
        return nom;
    }


    /** @return le main du joueur */
    public Carte[] mainJoueur(){
        return mainDuJoueur;
    }

    /** @return la position du joueur */
    public int getPosition() {
        return position;
    }


}