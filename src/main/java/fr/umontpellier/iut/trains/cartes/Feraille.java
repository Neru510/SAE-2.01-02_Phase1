package fr.umontpellier.iut.trains.cartes;

public abstract class Feraille extends Carte {
    public Feraille(String nom){
        super(nom,"Feraille","Gris");
    }
    public Feraille(String nom, int cout, int prix){
        super(nom, "Feraille", "Gris", cout, prix);
    }
}
