package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Aiguillage extends Action {
    public Aiguillage() {
        super("Aiguillage",5,0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.ajouterMain(2);
    }
}
