package fr.umontpellier.iut.trains.cartes;

public abstract class TrainAction extends Train {
    private final static String typeSecondaire = "Action";
    private int nbPrestige;
    private String actionDescriptif;

    public TrainAction(String nom){
        super(nom);
    }

    public TrainAction(String nom, int nbPrestige, int cout, int prix, String actionDescriptif) {
        super(nom, cout, prix);
        this.nbPrestige = nbPrestige;
        this.actionDescriptif = actionDescriptif;
    }

    public TrainAction(String nom, int cout, int prix, String actionDescriptif) {
        super(nom, cout, prix);
        this.actionDescriptif = actionDescriptif;
        this.nbPrestige = 0;
    }

    public String getTypeSecondaire(){
        return typeSecondaire;
    }

    public int getNbPrestige(){
        return nbPrestige;
    }

    public String getActionDescriptif(){
        return actionDescriptif;
    }

    public void modifierDescription(String actionDescriptif){
        this.actionDescriptif = actionDescriptif;
    }

    public void modifierNbPrestiges(int nbPrestige){
        this.nbPrestige = nbPrestige;
    }
}
