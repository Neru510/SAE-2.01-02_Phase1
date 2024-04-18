package fr.umontpellier.iut.trains.cartes;

public abstract class Train extends Carte {
    public Train(String nom, int cout, int prix){
        super(nom, "Train", "Bleu", cout, prix);
    }

    public Train(String nom, int cout, int prix, int nbPrestige){
        super(nom, "Train", "Bleu", cout, prix, nbPrestige);
    }
}
