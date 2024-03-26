package fr.umontpellier.iut.trains.cartes;

public abstract class Rail extends Carte{
    public Rail(String nom){
        super(nom,"Rail","Vert");
    }
    public Rail(String nom, int cout, int prix){
        super(nom, "Rail", "Vert", cout, prix);
    }
}
