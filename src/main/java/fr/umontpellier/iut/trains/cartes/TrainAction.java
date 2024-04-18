package fr.umontpellier.iut.trains.cartes;

public abstract class TrainAction extends Train {
    private final static String typeSecondaire = "Action";

    public TrainAction(String nom, int nbPrestige, int cout, int prix) {
        super(nom, cout, prix, nbPrestige);
    }

    public TrainAction(String nom, int cout, int prix, String actionDescriptif) {
        super(nom, cout, prix);
    }

    public String getTypeSecondaire(){
        return typeSecondaire;
    }

}
