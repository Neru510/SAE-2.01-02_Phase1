package fr.umontpellier.iut.trains.cartes;

public abstract class Action extends Carte{
    public Action(String nom) {
        super(nom, "Action", "Rouge");
    }
    public Action(String nom, int cout, int prixRevente, String action){
        super(nom, "Action", "Rouge", cout, prixRevente, action);
    }
}