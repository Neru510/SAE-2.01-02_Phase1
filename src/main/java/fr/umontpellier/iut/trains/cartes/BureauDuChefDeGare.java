package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

public class BureauDuChefDeGare extends Action {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 4, 0);
    }
    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.log("Choisissez une carte action que vous avez en main. Cette carte copie l'effet de la carte choisie.");
    }
}
