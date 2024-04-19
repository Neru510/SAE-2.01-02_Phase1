package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class SalleDeControle extends Action {
    public SalleDeControle() {
        super("Salle de contrôle", 7, 0, "Piochez 3 cartes");
    }
    @Override
    public void jouer(Joueur joueur){
        joueur.piocherEtAjouterMain(3);
    }
}
