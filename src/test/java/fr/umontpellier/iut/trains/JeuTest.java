package fr.umontpellier.iut.trains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @Test
    void test_getScoreTotal_1(){
        setupJeu();
        initialisation();
        assertEquals(0, jeu.getJoueurCourant().getScoreTotal());
    }

    @Disabled
    @Test
    void test_getScoreTotal_2(){
        //À FAIRE
    }

    @Test
    void test_run(){
        setupJeu();
        Collection<String> choix = new ArrayList<String>();
        for (int i = 0; i < tuiles.size(); i++){
            if (tuiles.get(i).estPosable()){
                choix.add("TUILES:" + i);
            }
        }
        System.out.println(choix);
        containsReference(choix, "TUILE:23");
    }
}
