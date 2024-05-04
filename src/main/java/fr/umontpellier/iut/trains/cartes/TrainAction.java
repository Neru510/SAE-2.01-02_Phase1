package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class TrainAction extends Carte {
    private final static String typeSecondaire = "Train";

    public TrainAction(String nom, int cout, int valeur) {
        super(nom, "TrainAction", "bleu", cout, valeur);
    }

    @Override
    public void jouer(Joueur joueur, boolean cabineConduc){
        jouer(joueur);
        joueur.ajouterArgent(-getvaleur());
    }

}
