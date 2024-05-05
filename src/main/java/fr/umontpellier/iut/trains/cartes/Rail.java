package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.Tuile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static fr.umontpellier.iut.trains.Jeu.isNumeric;

public abstract class Rail extends Carte{

    public Rail(String nom, int cout){
        super(nom, "Rail", "Vert", cout, 0);
    }

    @Override
    public void jouer(Joueur joueur, boolean enleveCarteFerraille) {
        super.jouer(joueur);
        Carte carte;

        if (!enleveCarteFerraille){
            carte = joueur.getJeu().prendreDansLaReserve("Ferraille");
            joueur.ajouterCartesRecues(carte);
        }
        joueur.ajouterPointsRails(1);
    }
}
