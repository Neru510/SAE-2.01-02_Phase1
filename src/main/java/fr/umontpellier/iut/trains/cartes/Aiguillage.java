package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Aiguillage extends Action {
    public Aiguillage() {
        super("Aiguillage",5,0,"Piochez deux cartes");
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.piocher(2);
    }
}
