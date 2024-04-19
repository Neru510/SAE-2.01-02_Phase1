package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Rail extends Carte{
    public Rail(String nom){
        super(nom,"Rail","Vert");
    }
    public Rail(String nom, int cout, String description){
        super(nom, "Rail", "Vert", cout, 0, description);
    }
    @Override
    public void jouer(Joueur joueur){
        Carte carte = joueur.getJeu().prendreDansLaReserve("Ferraille");
        joueur.ajouterCartesRecues(carte);
    }
}
