package jeu.dameDePique.tests;

import jeu.dameDePique.GestionAffichage;
import jeu.dameDePique.JoueurOrdi;
import jeu.dameDePique.Carte;
import static jeu.dameDePique.GestionAffichage.*;

public class TestJoueurOrdi {


    /**
     * test visuel de la methode testChoisirCarteAPoser
     * Dans ce test on a creer differentes mains que l'on va attribuer
     * au JoueurOrdi J2 et on le met dans differentes condition avec
     * differents centre de table afin de voir quelle carte il va choisir de poser
     * et ainsi savoir quel est sont choix et si c'est le bon
     */
    public static void testChoisirCarteAPoser() {

        JoueurOrdi J2 = new JoueurOrdi(1, "corenpin");


        /* L'on a créé 9 mains a tester : chacune a une particularité,
           elles sont chacune composée de 13 cartes, mais certaines contiennent des cartes nulles
           qui correspondent a l'avancée de la partie */
        final Carte[][] MAIN_A_TESTER = {



                // main de 13 cartes sans dame de pique
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },

                //main de 13 carte sans coeur ni dame de pique
                {new Carte(112), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(214),new Carte(108),new Carte(212),
                        new Carte(107),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },

                // main avec seulement des coeur
                {new Carte(302), new Carte(303), new Carte(304), new Carte(308),
                        new Carte(305),new Carte(307),new Carte(314),new Carte(311),
                        new Carte(313),new Carte(309),new Carte(0),new Carte(0),
                        new Carte(310) },

                // main avec une carte nulle
                {new Carte (0), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(312),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },

                // main avec de pique moins fort que la dame
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },

                // main avec des carte coeur forte et 4 cartes nulles
                {new Carte(312), new Carte(0), new Carte(0), new Carte(102),
                        new Carte(0),new Carte(412),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(414),new Carte(405),new Carte(0),
                        new Carte(314) },

                // main avec peu de carte en fin de partie
                {new Carte(0), new Carte(0), new Carte(0), new Carte(0),
                        new Carte(103),new Carte(0),new Carte(0),new Carte(211),
                        new Carte(308),new Carte(0),new Carte(0),new Carte(0),
                        new Carte(0) },

                // main avec une seule carte
                {new Carte(0), new Carte(0), new Carte(0), new Carte(0),
                        new Carte(0),new Carte(0),new Carte(0),new Carte(0),
                        new Carte(208),new Carte(0),new Carte(0),new Carte(0),
                        new Carte(0) },

                // main avec une seule carte contenant du coeur
                {new Carte(0), new Carte(0), new Carte(0), new Carte(0),
                        new Carte(0),new Carte(0),new Carte(0),new Carte(0),
                        new Carte(308),new Carte(0),new Carte(0),new Carte(0),
                        new Carte(0) },

        };


        /*
         * On a creer un tableau de 11 centre de table afin de tester le plus de
         * cas de figure possible
         */
        final Carte[][] EXEMPLE_DE_CENTRE = {

                // position du joueur sud
                {new Carte(208), new Carte(0), new Carte(202), new Carte(414)},
                // position du joueur est
                {new Carte(0), new Carte(209), new Carte(202), new Carte(414)},
                // position du joueur nord
                {new Carte(208), new Carte(202), new Carte(0), new Carte(414)},
                // position du joueur ouest
                {new Carte(208), new Carte(414), new Carte(202), new Carte(0)},
                // centre de la table qd on est le 1er joueur a jouer
                {new Carte(0), new Carte(0), new Carte(0), new Carte(0)},
                // centre quand on est le deuxieme joueur a jouer
                {new Carte(414), new Carte(0), new Carte(0), new Carte(0)},
                // centre quand on est le troisieme joueur a jouer
                {new Carte(308), new Carte(414), new Carte(0), new Carte(0)},
                // centre avec que des coeurs
                {new Carte(308), new Carte(0), new Carte(302), new Carte(0)},
                // centre avec la dame de pique
                {new Carte(0), new Carte(0), new Carte(0), new Carte(412)},
                // centre avec seulement des cartes faibles
                {new Carte(204), new Carte(203), new Carte(402), new Carte(0)},
                //centre avec seulement des cartes fortes
                {new Carte(0), new Carte(213), new Carte(412), new Carte(0)}

        };

        final int[] PREMIER_JOUEUR = { 0, 1, 3, 1, 2, 0, 1, 0, 3, 1, 1 };

        final int[] NUMERO_PLIS = { 1, 1, 3, 2, 1, 5, 11, 13, 13};

        System.out.println("Voici les tests visuels de ChoisirCarteAPoser() : \n" +
                "Veuillez verifier que les choix de carte de l'IA " +
                "sont les plus judicieux possible");
        for (int i = 0 ; i < EXEMPLE_DE_CENTRE.length ; i++) {
            for (int j = 0 ; j < MAIN_A_TESTER.length ; j++) {
                System.out.println("\n\n-------------------------------------------------\n\n");
                J2.creerMain(MAIN_A_TESTER[j]);
                afficherMainJoueur(J2.mainJoueur());
                GestionAffichage.afficherCentre(EXEMPLE_DE_CENTRE[i], PREMIER_JOUEUR[i]);
                System.out.println("\nLe premier joueur est le joueur n° : " + PREMIER_JOUEUR[i]);
                System.out.println("\nVoici les cartes posables : ");
                for (int z = 0; z < 13; z++) {
                    if (J2.cartePossible(EXEMPLE_DE_CENTRE[i], NUMERO_PLIS[j], true,
                            PREMIER_JOUEUR[i])[z].getNumCarte() != 0) {
                        System.out.print(J2.cartePossible(EXEMPLE_DE_CENTRE[i], NUMERO_PLIS[j],
                                true, PREMIER_JOUEUR[i])[z] + ", ");
                    }
                }
                System.out.println("\n\nL'ordinateur a choisi la carte : "
                        + J2.choisirCarteAPoser(EXEMPLE_DE_CENTRE[i], NUMERO_PLIS[j], true, PREMIER_JOUEUR[i]));

            }
        }
    }

    public static void testChoisirCarteAEchanger() {

        JoueurOrdi J1 = new JoueurOrdi(1, "lethlos");

        final Carte[][] MAIN_A_TESTER =  {
                // main de 13 cartes sans dame de pique
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },

                // main de 13 cartes avec dame de pique
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(412),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },

                // main de 13 cartes avec dame de pique en 1ere position
                {new Carte(412), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },

                // main de 13 cartes avec dame de pique en derniere position
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(412) },

                // main de 13 cartes avec dame de pique comme carte de plus haute valeur
                {new Carte(211), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(306),new Carte(108),new Carte(304),
                        new Carte(307),new Carte(412),new Carte(405),new Carte(406),
                        new Carte(210) },


                // test des mains de 12 cartes

                // main de 12 cartes sans dame de pique avec une carte new Carte (0)e
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte (0) ,new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },

                // main de 12 cartes sans dame de pique avec une carte new Carte (0)e en derniere position
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(210),new Carte(209),new Carte(405),new Carte(406),
                        new Carte (0) },

                // main de 12 cartes sans dame de pique avec une carte new Carte (0)e en 1ere position
                {new Carte (0), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(312),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },



                // main de 12 cartes avec dame de pique avec une carte new Carte (0)e
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(412),new Carte(108),new Carte(212),
                        new Carte(307),new Carte (0),new Carte(405),new Carte(406),
                        new Carte(210) },

                // main de 12 cartes avec dame de pique avec une carte new Carte (0)e en en 1ere position
                {new Carte (0), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(412),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte(210) },

                // main de 12 cartes avec dame de pique avec une carte new Carte (0)e en derniere position
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(412),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte (0) },

                // main de 12 cartes avec dame de pique en 1ere position avec une carte new Carte (0)e en dernier
                {new Carte(412), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte (0) },

                //  main de 11 carte donc 2 cartes nulle


                // main de 11 cartes sans dame de pique
                {new Carte(312), new Carte (0), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte (0),new Carte(406),
                        new Carte(210) },

                // main de 11 cartes sans dame de pique mais avec carte new Carte (0)e en debut et fin
                {new Carte (0), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(413),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(209),new Carte(405),new Carte(406),
                        new Carte (0) },

                // main de 11 cartes avec dame de pique et deux cartes new Carte (0)es
                {new Carte(312), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(412),new Carte(108),new Carte(212),
                        new Carte(307),new Carte (0),new Carte (0),new Carte(406),
                        new Carte(210) },

                // main de 11 cartes avec dame de pique et carte new Carte (0)e en 1ere position et derniere position
                {new Carte (0), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte(314),new Carte(108),new Carte(212),
                        new Carte(307),new Carte(412),new Carte(405),new Carte(406),
                        new Carte (0) },

                // main de 11 cartes avec dame de pique comme carte de plus haute valeur
                {new Carte(412), new Carte(208), new Carte(109), new Carte(102),
                        new Carte(202),new Carte (0),new Carte(108),new Carte(304),
                        new Carte(307),new Carte (0),new Carte(405),new Carte(406),
                        new Carte(210) }


        };

        final Carte[] RESULTAT_ATTENDUS_MANCHE_DIFF_0 = {


                new Carte(314),
                new Carte(312),
                new Carte(314),
                new Carte(314),
                new Carte(412),
                new Carte(314),
                new Carte(314),
                new Carte(314),
                new Carte(312),
                new Carte(412),
                new Carte(312),
                new Carte(314),
                new Carte(314),
                new Carte(413),
                new Carte(312),
                new Carte(314),
                new Carte(412)

        };

        final Carte[] RESULTAT_ATTENDUS_MANCHE_0 = {


                new Carte(314),
                new Carte(312),
                new Carte(314),
                new Carte(314),
                new Carte(211),
                new Carte(314),
                new Carte(314),
                new Carte(314),
                new Carte(312),
                new Carte(212),
                new Carte(312),
                new Carte(314),
                new Carte(314),
                new Carte(413),
                new Carte(312),
                new Carte(314),
                new Carte(210)

        };

        final int[] NUM_MANCHE = { 0, 1, 2 , 3 };


        for(int i = 0 ; i < NUM_MANCHE.length ; i++) {
            for(int j = 0 ; j < MAIN_A_TESTER.length ; j++ ) {
                J1.creerMain(MAIN_A_TESTER[j]);
                if( NUM_MANCHE[i] == 0 ) {
                    if (J1.choisirCarteAEchanger(NUM_MANCHE[i]).getNumCarte()
                            == RESULTAT_ATTENDUS_MANCHE_0[j].getNumCarte()) {

                        System.out.println("Test reussi");
                    } else {
                        System.err.println("Erreur : la carte de la main " + j + " retournée est la n° : " +
                                J1.choisirCarteAEchanger(NUM_MANCHE[i]).getNumCarte() + "alors" +
                                "que l'on attendait " + RESULTAT_ATTENDUS_MANCHE_0[j].getNumCarte());
                    }
                } else {
                    if( J1.choisirCarteAEchanger(NUM_MANCHE[i]).getNumCarte()
                            == RESULTAT_ATTENDUS_MANCHE_DIFF_0[j].getNumCarte()) {

                        System.out.println("Test reussi");

                    } else {

                        System.err.println("Erreur : la carte de la main " + j + " retournée est la n° : " +
                                J1.choisirCarteAEchanger(NUM_MANCHE[i]).getNumCarte() + "alors" +
                                "que l'on attendait " + RESULTAT_ATTENDUS_MANCHE_DIFF_0[j].getNumCarte());
                    }
                }
            }
        }
    }


    public static void main(String [] args) {

         testChoisirCarteAEchanger();
        // testChoisirCarteAPoser();

    }
}
