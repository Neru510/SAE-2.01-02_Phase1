package fr.umontpellier.iut.trains.cartes;

public abstract class Victoire extends Carte {
    private int nbPrestige;

    public Victoire(String nom){
        super(nom,"Victoire","Jaune");
    }
    public Victoire(String nom, int nbPrestige, int cout, int vente) {
        super(nom,"Victoire","Jaune", cout, vente);
        this.nbPrestige = nbPrestige;
    }
    public int getNbPrestige(){
        return nbPrestige;
    }
    public void modifierNbPrestige(int nbPrestige){
        this.nbPrestige = nbPrestige;
    }
}
