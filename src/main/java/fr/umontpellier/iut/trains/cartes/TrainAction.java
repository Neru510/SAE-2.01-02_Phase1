package fr.umontpellier.iut.trains.cartes;

public abstract class TrainAction extends Train {
    private final static String typeSecondaire = "Action";

    public TrainAction(String nom, int nbPrestige, int cout, int valeur) {
        super(nom, cout, valeur, nbPrestige);
    }

    public TrainAction(String nom, int cout, int valeur, String actionDescriptif) {
        super(nom, cout, valeur);
    }

    public TrainAction(String nom, int cout, int valeur) {
        super(nom, cout, valeur);
    }

    public String getTypeSecondaire(){
        return typeSecondaire;
    }

}
