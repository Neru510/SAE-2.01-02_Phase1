package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PassageEnGare extends Action {
    public PassageEnGare() {
        super("Passage en gare", 3, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.piocherEtAjouterMain();
    }
}
