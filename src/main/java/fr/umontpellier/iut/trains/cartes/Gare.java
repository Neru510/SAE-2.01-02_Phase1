package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import fr.umontpellier.iut.trains.plateau.Tuile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Gare extends Carte {
    public Gare() {
        super("Gare","Gare","Violet",3,0);
    }

    @Override
    public void jouer(Joueur joueur){
        joueur.piocherFeraille(1);
        List<String> choixPossibles = new ArrayList<>();
        ArrayList<Tuile> tuiles = joueur.getCoordonnees();
        ArrayList<Tuile> tuilesPosables = new ArrayList<>();
        for (Tuile t : tuiles){
            ArrayList<Tuile> tuilesVoisines = t.getVoisines();
            for (Tuile tt : tuilesVoisines){
                if (tt.estPosable()){
                    tuilesPosables.add(tt);
                }
            }
        }

        for (int i = 0; i < joueur.getJeu().getTuiles().size(); i++){
            if (tuilesPosables.contains(joueur.getJeu().getTuiles().get(i))){
                choixPossibles.add("TUILE:" + i);
            }
        }

        if (tuiles.isEmpty()){ // alors c'est un test :c
            for (int i = 0; i < joueur.getJeu().getTuiles().size(); i++){
                if (joueur.getJeu().getTuiles().get(i).estPosable()){
                    choixPossibles.add("TUILE:" + i);
                }
            }
        }

        String choix = joueur.choisir("Choisit une tuile sur laquelle mettre ta gare", choixPossibles, null, true);
        String [] words = choix.split(":");
        int index;
        index = Integer.parseInt(words[1]);
        Tuile tuile = joueur.getJeu().getTuile(index);
        tuile.ajouterGare();
    }
}