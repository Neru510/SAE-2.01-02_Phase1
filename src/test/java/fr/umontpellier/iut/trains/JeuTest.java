package fr.umontpellier.iut.trains;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

public class JeuTest extends BaseTestClass{

    @Test
     void test_est_Fini_1(){
        setupJeu();
        initialisation();
        //jeu.modifierNbJetonGare(0); // fonction dans jeu
        assertTrue(this.jeu.estFini());
    }

    @Test
    void test_est_Fini_2(){
        setupJeu();
        initialisation();
        //jeu.getJoueurCourant().modifierNbJetonsRails(0); // fonction dans joueur
        assertTrue(this.jeu.estFini());
    }

    @Test
    void test_estFini_3(){
        setupJeu();
        initialisation();
        //jeu.modifierNbCartesReserve(4); // fonction dans jeu
        boolean check = jeu.estFini();
        assertTrue(this.jeu.estFini());
    }
}
