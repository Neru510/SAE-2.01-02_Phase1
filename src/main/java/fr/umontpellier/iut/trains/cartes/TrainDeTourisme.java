package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainDeTourisme extends TrainAction {
    public TrainDeTourisme() {
        super("Train de tourisme", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.ajouterPointScoreTotal(1);
    }
}
