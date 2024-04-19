package fr.umontpellier.iut.trains.cartes;

public abstract class Train extends Carte {
    public Train(String nom, int cout, int valeur){
        super(nom, "Train", "Bleu", cout, valeur);
    }

    public Train(String nom, int cout, int valeur, int nbPrestige){
        super(nom, "Train", "Bleu", cout, valeur, nbPrestige);
    }
}
