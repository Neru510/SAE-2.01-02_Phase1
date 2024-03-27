package fr.umontpellier.iut.trains.cartes;

public abstract class Action extends Carte{
    public Action(String nom) {
        super(nom, "Action", "Rouge");
    }
    public Action(String nom, int cout, int prix, String action){
        super(nom, "Action", "Rouge", cout, prix, action);
    }
}