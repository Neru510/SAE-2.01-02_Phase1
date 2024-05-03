package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Victoire extends Carte {

    public Victoire(String nom, int nbPrestige, int cout, int vente) {
        super(nom,"Victoire","Jaune", cout, vente, nbPrestige);
        this.setFeraille(true);
    }

    @Override
    public void jouer(Joueur joueur){}
}
