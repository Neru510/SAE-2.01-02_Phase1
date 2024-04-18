package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Gare extends Carte {
    public Gare() {
        super("Gare","Gare","Violet",3,0);
    }

    @Override
    public void jouer(Joueur joueur){
        joueur.piocherFeraille(1);
    }
}