package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Rail extends Carte{

    public Rail(String nom, int cout){
        super(nom, "Rail", "Vert", cout, 0);
    }
    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        Carte carte = joueur.getJeu().prendreDansLaReserve("Ferraille");
        joueur.ajouterCartesRecues(carte);
        //joueur.ajouterPointsRails(1);
    }
}
