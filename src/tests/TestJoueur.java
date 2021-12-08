package jeu.dameDePique.tests;


import jeu.dameDePique.Joueur;
import jeu.dameDePique.Carte;
import java.util.Arrays;
import static jeu.dameDePique.GestionAffichage.*;

public class TestJoueur {


    private static final Carte[] mainVide = new Carte[13];

    private static final Joueur[] JEU_ESSAI = {

            new Joueur(3, "lethlos"),
            new Joueur(0, "corenpin"),
            new Joueur(1, "loupes"),
            new Joueur(2, "penalty"),
            new Joueur(3, "Audrey"),
            new Joueur(0, "goataga"),
            new Joueur(1, "rufus14"),
            new Joueur(2, "guigui"),
    };

    public static void testCreerMain() {

        boolean estValide;
        estValide = true;

        final Carte[][] MAIN_VALIDE = {

                //ordre croissant
                { new Carte(102), new Carte(103), new Carte(108), new Carte(113),
                        new Carte(114), new Carte(202), new Carte(203), new Carte(214),
                        new Carte(302), new Carte(308), new Carte(314), new Carte(402),
                        new Carte(402) },

                //ordre decroissant
                { new Carte(409), new Carte(407), new Carte(404), new Carte(402),
                        new Carte(308), new Carte(307), new Carte(303), new Carte(206),
                        new Carte(205), new Carte(202), new Carte(110), new Carte(106),
                        new Carte(103) },

                // ordre aléatoire
                { new Carte(212), new Carte(313), new Carte(404), new Carte(302),
                        new Carte(310), new Carte(102), new Carte(307), new Carte(209),
                        new Carte(108), new Carte(113), new Carte(305), new Carte(414),
                        new Carte(303)},

                /* on va tester tout les cartes des differents types */

                // cartes trefles
                { new Carte(102), new Carte(103), new Carte(104), new Carte(105),
                        new Carte(106), new Carte(107), new Carte(108), new Carte(109),
                        new Carte(110), new Carte(111), new Carte(112), new Carte(113),
                        new Carte(114)},

                // cartes carreaux
                { new Carte(202), new Carte(203), new Carte(204), new Carte(205),
                        new Carte(206), new Carte(207), new Carte(208), new Carte(209),
                        new Carte(210), new Carte(211), new Carte(212), new Carte(213),
                        new Carte(214) },

                // cartes coeurs
                { new Carte(302), new Carte(303), new Carte(304), new Carte(305),
                        new Carte(306), new Carte(307), new Carte(308), new Carte(309),
                        new Carte(310), new Carte(311), new Carte(312), new Carte(313),
                        new Carte(314)},

                // cartes piques
                { new Carte(402), new Carte(403), new Carte(404), new Carte(405),
                        new Carte(406), new Carte(407), new Carte(408), new Carte(409),
                        new Carte(410), new Carte(411), new Carte(412), new Carte(413),
                        new Carte(414) },

                // test carte 0
                { new Carte(102), new Carte (0), new Carte (108), new Carte (113),
                        new Carte (114), new Carte (202), new Carte (203), new Carte (214),
                        new Carte (302), new Carte (308), new Carte (314), new Carte (0),
                        new Carte (414) }

        };

        final Carte[][] MAIN_INVALIDE = {

                {}, // tableau vide

                // plus de 13 cartes
                { new Carte(409), new Carte (407), new Carte (404), new Carte (402),
                        new Carte (308), new Carte (307), new Carte (303), new Carte (206),
                        new Carte (205), new Carte (202), new Carte (110), new Carte (106),
                        new Carte (102), new Carte (213), new Carte (114) },

                // moins que 13 cartes
                { new Carte(212), new Carte (313), new Carte (404), new Carte (302),
                        new Carte (310), new Carte (102), new Carte (307), new Carte (209),
                        new Carte (108), new Carte (303) },
        };


        for (int i = 0; i < MAIN_VALIDE.length; i++) {

            Joueur J2 = new Joueur(2, "lethlos");
            System.out.println(Arrays.toString(J2.mainJoueur()));
            J2.creerMain(MAIN_VALIDE[i]);
            System.out.println(Arrays.toString(J2.mainJoueur()));

            for (int k = 0; k < MAIN_VALIDE.length; k++) {

                if (MAIN_VALIDE[i][k] == J2.mainJoueur()[k]) {

                } else {
                    estValide = false;
                }
            }

            if (!estValide) {
                System.err.println("Y'a une erreur sur la methode creerMain au tableau " + i);
            } else {
                System.out.println("test creerMain reussi au tableau numéro :" + i);
            }
        }

        for (int i = 0; i < MAIN_INVALIDE.length; i++) {

            Joueur J2 = new Joueur(2, "lethlos");
            System.out.println(Arrays.toString(J2.mainJoueur()));
            try {
                J2.creerMain(MAIN_INVALIDE[i]);
                System.err.println(Arrays.toString(MAIN_INVALIDE[i]) + " n'aurait pas du passer les tests ");
            }catch(Exception e) {
                System.out.println("Le tableau " + i + " a bien été attrapé");
            }
        }
    }

    public static void testModifierScore() {

        final int[] QUANTITES = { 6, 8, -4, -12 };

        final int[] SCORES_FINAUX_ATTENDUS = { 6, 14, 10, 0 };

        Joueur J1 = new Joueur(1, "guigui");

        /*
         * passage numero 1 : test de l'ajout d'une quantite au score par defaut du joueur
         * passage numero 2 : test de l'ajout d'une quantite au score deja modifier antérieurement
         * passage numero 3 : test du retrait d'une quantité
         * passage numero 4 : test du retrait d'une quantité supérieure au score actuel
         */
        for(int i = 0; i < QUANTITES.length ; i++ ) {

            J1.ajouterScore(QUANTITES[i]);

            if (J1.getScore() == SCORES_FINAUX_ATTENDUS[i]) {
                System.out.println("test modifierScorereussi");
            } else {
                System.err.println("erreur sur la methode modifierScore : QUANTITE "+ i);
            }
        }
    }

    public static void testEnleverCarte() {

        boolean estValide;

        estValide = true;

        Joueur J1 = new Joueur(3, "guigui");

        final Carte[] MAIN = {

                new Carte(102), new Carte (103), new Carte (108),
                new Carte (113), new Carte (114), new Carte (202), new Carte (203),
                new Carte (214), new Carte (302), new Carte (308), new Carte (314),
                new Carte (402), new Carte (414)
        };

        final Carte[] MAIN_ATTENDU = {

                new Carte(0), new Carte (103), new Carte (108), new Carte (113),
                new Carte (114), new Carte (202), new Carte (0), new Carte (214),
                new Carte (302), new Carte (308), new Carte (314), new Carte (402),
                new Carte (0)
        };

        J1.creerMain(MAIN);

        /* test de la partie valide */
        J1.enleverCarte(MAIN[0]);
        J1.enleverCarte(MAIN[6]);
        J1.enleverCarte(MAIN[12]);

        for(int i = 0 ; i < MAIN.length ; i++) {
            if(!(J1.mainJoueur()[i].getNumCarte() == MAIN_ATTENDU[i].getNumCarte())) {
                estValide = false;
                System.err.println("Test de la main du joueur pas reussi " + i );
            }
        }

        if(estValide) {
            System.out.println("test EnleverCarte reussi");
        }

        /*
         * test de la partie invalide :
         * on essaie d'enlever une carte existante mais qui n'est pas dans la main
         */
        try {
            J1.enleverCarte( new Carte(107));
            System.err.println("Erreur carte invalide a été enlevée");
        }catch (Exception e) {
            System.out.println("La carte invalide sur enleverCarte a été attrapée");
        }

        /* on essaie d'enlever une carte qui n'existe pas */
        try {
            J1.enleverCarte(new Carte(920));
            System.err.println("Erreur carte invalide a été enlevée");
        } catch (Exception e) {
            System.out.println("La carte invalide sur enleverCarte a été attrapée");
        }

        /* on essaie d'enlever la carte 0 qui correspond a la valeur nulle */
        try {
            J1.enleverCarte(new Carte(0));
            System.err.println("Erreur carte invalide a été enlevée");
        } catch (Exception e) {
            System.out.println("La carte invalide sur enleverCarte a été attrapée");
        }
    }

    /**
     *
     */
    public static void testCartePossible() {

        final boolean COEUR_TOMBE[] = {true, false};

        final Carte[][] MAIN_POSSIBLE = {

                /* Test d'une main contenant que des trèfles */
                {new Carte(102), new Carte(103),
                        new Carte(104), new Carte(105),
                        new Carte(106), new Carte(107),
                        new Carte(108), new Carte(109),
                        new Carte(110), new Carte(111),
                        new Carte(112), new Carte(113),
                        new Carte(114)},

                /* Test d'une main contenant que des coeurs */
                {new Carte(302), new Carte(303),
                        new Carte(304), new Carte(305),
                        new Carte(306), new Carte(307),
                        new Carte(308), new Carte(309),
                        new Carte(310), new Carte(311),
                        new Carte(312), new Carte(313),
                        new Carte(314)},

                /* Test d'une main avec 13 cartes avec le 2 de Trèfles */
                {new Carte(308), new Carte(303),
                        new Carte(304), new Carte(109),
                        new Carte(306), new Carte(307),
                        new Carte(114), new Carte(309),
                        new Carte(310), new Carte(102),
                        new Carte(405), new Carte(313),
                        new Carte(314)},

                /* Test d'une main avec 13 cartes sans le 2 de Trèfles */
                {new Carte(308), new Carte(303),
                        new Carte(304), new Carte(109),
                        new Carte(306), new Carte(307),
                        new Carte(114), new Carte(309),
                        new Carte(310), new Carte(202),
                        new Carte(405), new Carte(313),
                        new Carte(314)},

                /* Test d'une main avec 13 cartes sans Trèfles */
                {new Carte(302), new Carte(203),
                        new Carte(404), new Carte(205),
                        new Carte(206), new Carte(407),
                        new Carte(208), new Carte(209),
                        new Carte(310), new Carte(211),
                        new Carte(212), new Carte(213),
                        new Carte(414)},

                /* Test d'une main non complète */
                {new Carte(302), new Carte(203),
                        new Carte(404), new Carte(205),
                        new Carte(206), new Carte(407),
                        new Carte(208), new Carte(209),
                        new Carte(0), new Carte(0),
                        new Carte(0), new Carte(0),
                        new Carte(0)},

                /* Test d'une main incomplète sans Pique */
                {new Carte(302), new Carte(103),
                        new Carte(304), new Carte(105),
                        new Carte(106), new Carte(107),
                        new Carte(208), new Carte(309),
                        new Carte(110), new Carte(311),
                        new Carte(0), new Carte(0),
                        new Carte(0)},

                /* Test d'une main avec une carte */
                {new Carte(410), new Carte(0),
                        new Carte(0), new Carte(0),
                        new Carte(0), new Carte(0),
                        new Carte(0), new Carte(0),
                        new Carte(0), new Carte(0),
                        new Carte(0), new Carte(0),
                        new Carte(0)},

        };

        final  Carte[][] CENTRE_POSSIBLE = {

                /*Centre Vide, on est le premier joueur*/
                {new Carte(0), new Carte(0),
                        new Carte(0), new Carte(0)},

                /* On est le second Joueur*/
                {new Carte(410), new Carte(0),
                        new Carte(0), new Carte(0)},

                {new Carte(0), new Carte(0),
                        new Carte(102), new Carte(0)},

                {new Carte(0), new Carte(302),
                        new Carte(0), new Carte(0)},

                /* On est le 3ème Joueur */
                {new Carte(203), new Carte(211),
                        new Carte(0), new Carte(0)},

                {new Carte(0), new Carte(0),
                        new Carte(305), new Carte(310)},

                {new Carte(410), new Carte(0),
                        new Carte(0), new Carte(202)},

                /*On est le 4 ème Joueur*/
                {new Carte(0), new Carte(104),
                        new Carte(108), new Carte(210)},

                {new Carte(410), new Carte(412),
                        new Carte(304), new Carte(0)},

        };


        int numPli;
        int positionPremierJoueur;

        Joueur J1 = new Joueur(2, "Guillaume");

        for (Carte[] mainATester : MAIN_POSSIBLE) {
            for(Carte[] centrePossible : CENTRE_POSSIBLE) {

                /*On calcul le numPli*/
                numPli = 1;
                for(Carte carte : mainATester) {
                    if (carte.getNumCarte() == 0) {
                        numPli ++;
                    }
                }

                /*On calcul la position du premier Joueur*/
                positionPremierJoueur = 0;
                for(Carte carte : centrePossible) {
                    if (carte.getNumCarte() == 0) {
                        positionPremierJoueur ++;
                    }
                }

                /* Si positionPremierJoueur = 4 alors
                 * c'est que nous somme le premier joueur
                 * */
                if (positionPremierJoueur > 3) {
                    positionPremierJoueur = J1.getPosition();
                }

                try {

                } catch (Exception e) {

                }

            }
        }
    }

    /**
     * Autre manière de déterminer l'ensemble des cartes possibles
     */
    public static Carte[] FakeCartePossible(Carte[] centreTable, int numPlis,
                                         boolean coeurTombe,
                                         int positionPremierJoueur,
                                         Carte[] mainJoueur){

        final Carte[] CARTE_TREFLE_POSSIBLE = {
                new Carte(102), new Carte(103),
                new Carte(104), new Carte(105),
                new Carte(106), new Carte(107),
                new Carte(108), new Carte(109),
                new Carte(110), new Carte(111),
                new Carte(112), new Carte(113),
                new Carte(114)
        };

        final Carte[] CARTE_CARREAU_POSSIBLE = {
                new Carte(202), new Carte(203),
                new Carte(204), new Carte(205),
                new Carte(206), new Carte(207),
                new Carte(208), new Carte(209),
                new Carte(210), new Carte(211),
                new Carte(212), new Carte(213),
                new Carte(214)
        };

        final Carte[] CARTE_COEUR_POSSIBLE = {
                new Carte(302), new Carte(303),
                new Carte(304), new Carte(305),
                new Carte(306), new Carte(307),
                new Carte(308), new Carte(309),
                new Carte(310), new Carte(311),
                new Carte(312), new Carte(313),
                new Carte(314)
        };

        final Carte[] CARTE_PIQUE_POSSIBLE = {
                new Carte(402), new Carte(403),
                new Carte(404), new Carte(405),
                new Carte(406), new Carte(407),
                new Carte(408), new Carte(409),
                new Carte(410), new Carte(411),
                new Carte(412), new Carte(413),
                new Carte(414)
        };

        final Carte[] PREMIER_CARTE_JOUE = {new Carte(102)};

        Carte[] cartePossible = new Carte[13];

        int indexCartePossible;

        indexCartePossible = 0;

        if (numPlis == 1 && centreTable[positionPremierJoueur].getNumCarte() == 0) {
            cartePossible = PREMIER_CARTE_JOUE;
        } else if (numPlis == 1 && centreTable[positionPremierJoueur].getNumCarte() != 0) {
            for (Carte carte : CARTE_TREFLE_POSSIBLE) {
                for (Carte value : mainJoueur) {
                    if (value.getNumCarte() == carte.getNumCarte()) {
                        cartePossible[indexCartePossible] = value;
                        indexCartePossible ++;
                    }
                }
            }
        }

        /* si la première carte est vide ça signifie
         * soit que ce n'est pas le premier pli
         * soit que le joueur n'avais pas de Trèfle
         *
         * On vérifie donc si le joueur n'avait pas de trèfle*/
        if (cartePossible[0].getNumCarte() == 0 && numPlis == 1) {
            for (Carte carte : CARTE_CARREAU_POSSIBLE) {
                for (Carte value : mainJoueur) {
                    if (value.getNumCarte() == carte.getNumCarte()) {
                        cartePossible[indexCartePossible] = value;
                        indexCartePossible ++;
                    }
                }
            }
            for (Carte carte : CARTE_PIQUE_POSSIBLE) {
                for (Carte value : mainJoueur) {
                    if (value.getNumCarte() == carte.getNumCarte() && value.getNumCarte() != 412) {
                        cartePossible[indexCartePossible] = value;
                        indexCartePossible ++;
                    }
                }
            }
        }

        /* si ce n'est pas le premier pli et que nous somme le premier joueur
         * On peut jouer toutes nos cartes si un coeur est déjà tombé.
         * Sinon on ne peut pas jouer de coeur
         */
        if (cartePossible[0].getNumCarte() == 0 && centreTable[positionPremierJoueur].getNumCarte() == 0) {
            if(coeurTombe) {
                cartePossible = mainJoueur;
            } else {
                for (Carte carte : CARTE_CARREAU_POSSIBLE) {
                    for (Carte value : mainJoueur) {
                        if (value.getNumCarte() == carte.getNumCarte()) {
                            cartePossible[indexCartePossible] = value;
                            indexCartePossible ++;
                        }
                    }
                }
                for (Carte carte : CARTE_PIQUE_POSSIBLE) {
                    for (Carte value : mainJoueur) {
                        if (value.getNumCarte() == carte.getNumCarte()) {
                            cartePossible[indexCartePossible] = value;
                            indexCartePossible ++;
                        }
                    }
                }
                for (Carte carte : CARTE_TREFLE_POSSIBLE) {
                    for (Carte value : mainJoueur) {
                        if (value.getNumCarte() == carte.getNumCarte()) {
                            cartePossible[indexCartePossible] = value;
                            indexCartePossible ++;
                        }
                    }
                }
            }
        }

        /* Si ce n'est pas le premier Joueur*/
        if (cartePossible[0].getNumCarte() == 0 && centreTable[positionPremierJoueur].getNumCarte() != 0) {
            switch(centreTable[positionPremierJoueur].couleur()) {
                case "Coeur" -> {
                    for (Carte carte : CARTE_COEUR_POSSIBLE) {
                        for (Carte value : mainJoueur) {
                            if (value.getNumCarte() == carte.getNumCarte()) {
                                cartePossible[indexCartePossible] = value;
                                indexCartePossible ++;
                            }
                        }
                    }
                }
                case "Carreau" -> {
                    for (Carte carte : CARTE_CARREAU_POSSIBLE) {
                        for (Carte value : mainJoueur) {
                            if (value.getNumCarte() == carte.getNumCarte()) {
                                cartePossible[indexCartePossible] = value;
                                indexCartePossible ++;
                            }
                        }
                    }
                }
                case "Pique" -> {
                    for (Carte carte : CARTE_PIQUE_POSSIBLE) {
                        for (Carte value : mainJoueur) {
                            if (value.getNumCarte() == carte.getNumCarte()) {
                                cartePossible[indexCartePossible] = value;
                                indexCartePossible ++;
                            }
                        }
                    }
                }
                case "Trèfle" -> {
                    for (Carte carte : CARTE_TREFLE_POSSIBLE) {
                        for (Carte value : mainJoueur) {
                            if (value.getNumCarte() == carte.getNumCarte()) {
                                cartePossible[indexCartePossible] = value;
                                indexCartePossible ++;
                            }
                        }
                    }
                }
            }

            /* si malgré tout il n'a aucune carte possible
             * il peut jouer toute les cartes de sa main
             */
            if (cartePossible[0].getNumCarte() == 0) {
                cartePossible = mainJoueur;
            }
        }

        return cartePossible;





    }


    public static void testAjouterCarte(){

        boolean estValide;

        estValide = true;

        final Carte[] MAIN = {
                new Carte (102), new Carte (103),
                new Carte (108), new Carte (113),
                new Carte (114), new Carte (202),
                new Carte (203), new Carte (214),
                new Carte (302), new Carte (308),
                new Carte (314), new Carte (402),
                new Carte (414)

        };

        final Carte[] CARTE_A_AJOUTER = {
                new Carte(213),
                new Carte (111),
                new Carte (408) };

        final Carte[] MAIN_ATTENDU = {

                new Carte(213), new Carte (103),
                new Carte (108), new Carte (113),
                new Carte (114), new Carte (202),
                new Carte (111), new Carte (214),
                new Carte (302), new Carte (308),
                new Carte (314), new Carte (402),
                new Carte (408)
        };



        Joueur J1 = new Joueur(3, "guigui");
        J1.creerMain(MAIN);

        /* on enleve des cartes de la main pour pouvoir en ajouter */
        J1.enleverCarte(MAIN[0]);
        J1.enleverCarte(MAIN[6]);
        J1.enleverCarte(MAIN[12]);

        /* on ajoute des cartes */
        for(int i = 0 ; i < CARTE_A_AJOUTER.length ; i++) {
            J1.ajouterCarte(CARTE_A_AJOUTER[i]);
        }

        /* on verifie que les cartes ont bien été ajoutées */
        for(int j = 0 ; j < MAIN_ATTENDU.length ; j++) {
            if (!(MAIN_ATTENDU[j].getNumCarte() == J1.mainJoueur()[j].getNumCarte())) {
                estValide = false;
                System.err.println("La carte n'a pas été ajoutée a l'emplacement : " + j );
            }
        }

        if(estValide) {
            System.out.println("test AjouterCarte reussi");
        }


        /* ---------------- on test l'ajout de carte invalide ---------------- */

        /* on essaye d'ajouter une carte valide alors que la main est pleine */
        try {
            J1.ajouterCarte( new Carte (312));
            System.err.println("Erreur la main contient une carte de trop");
        }catch (Exception e) {
            System.out.println("La carte en trop n'a pas été ajoutée");
        }

        /* on enleve des cartes de la main pour pouvoir en ajouter */
        J1.enleverCarte(J1.mainJoueur()[0]);
        J1.enleverCarte(J1.mainJoueur()[6]);
        J1.enleverCarte(J1.mainJoueur()[12]);

        /* on va essayer d'ajouter une carte qui est deja dans la main */
        try {
            J1.ajouterCarte( new Carte(308));
            System.err.println("Erreur la main contient deux cartes identiques");
        }catch (Exception e) {
            System.out.println("La carte n'a pas été ajoutée");
        }
    }

    public static void testGetScore() {

        final int[] SCORE_ATTENDU
                = { 12, 23, 45, 67, 34, 98 , 45};

        Joueur J1 = new Joueur(3, "guigui");

        if (J1.getScore() == 0) {
            System.out.println("Le score est bien initialisé a 0");
        } else {
            System.err.println("Le score n'est pas égal a 0");
        }

        for(int i = 0 ; i < SCORE_ATTENDU.length; i++){
            J1.ajouterScore(SCORE_ATTENDU[i]);
            if(J1.getScore() == SCORE_ATTENDU[i]) {
                System.out.println("Le getter donne le resultat attendu");
            } else {
                System.err.println("Le score renvoyé n'est pas egal a " + SCORE_ATTENDU[i]);
            }
            J1.ajouterScore(-SCORE_ATTENDU[i]);
        }
    }

    public static void testmainJoueur() {

        boolean estValide;

        estValide = true;

        final Carte[][] MAIN_VALIDE = {

                //ordre croissant
                { new Carte (102), new Carte (103),
                        new Carte (108), new Carte (113),
                        new Carte (114), new Carte (202),
                        new Carte (203), new Carte (214),
                        new Carte (302), new Carte (308),
                        new Carte (314), new Carte (402),
                        new Carte (414) },

                //ordre decroissant
                { new Carte (409), new Carte (407),
                        new Carte (404), new Carte (402),
                        new Carte (308), new Carte (307),
                        new Carte (303), new Carte (206),
                        new Carte (205), new Carte (202),
                        new Carte (110), new Carte (106),
                        new Carte (102) },

                // ordre aléatoire
                { new Carte (212), new Carte (313),
                        new Carte (404), new Carte (302),
                        new Carte (310), new Carte (102),
                        new Carte (307), new Carte (209),
                        new Carte (108), new Carte (113),
                        new Carte (305), new Carte (414),
                        new Carte (303) }
        };

        Joueur J1 = new Joueur(2, "guigui");

        for(int i = 0; i < MAIN_VALIDE.length ; i++){
            J1.creerMain(MAIN_VALIDE[i]);
            for(int j = 0 ; j < MAIN_VALIDE[i].length ; j++ ) {
                if(!(MAIN_VALIDE[i][j] == J1.mainJoueur()[j] )){
                    estValide = false;
                    System.err.println("La main renvoyée n'est pas celle attendu");
                }
            }
            if(estValide){
                System.out.println("La main "+ i + " a correctement été renvoyée");
            }
        }
    }

    public static void testGetNom() {

        final String[] PSEUDO_ATTENDU
                = { "lethlos", "corenpin", "loupes", "penalty", "Audrey", "goataga", "rufus14", "guigui" };

        int noJeu;

        for (noJeu = 0 ; noJeu < PSEUDO_ATTENDU.length
                && PSEUDO_ATTENDU[noJeu] == JEU_ESSAI[noJeu].getNom() ;
             noJeu++)
            ;

        if (noJeu < PSEUDO_ATTENDU.length) {
            System.err.println("testGetPseudo() nok : erreur jeu no " + noJeu);
        } else {
            System.out.println("testGetPseudo() reussi");
        }

    }

    public static void main(String[] args) {

        testCreerMain();
        testModifierScore();
        testEnleverCarte();
        testAjouterCarte();
        testGetScore();
        testGetNom();
        testmainJoueur();
        testCartePossible();

    }
}