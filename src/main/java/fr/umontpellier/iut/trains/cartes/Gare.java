package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import fr.umontpellier.iut.trains.plateau.Tuile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static fr.umontpellier.iut.trains.Jeu.isNumeric;

public class Gare extends Carte {
    public Gare() {
        super("Gare","Gare","Violet",3,0);
    }

    @Override
    public void jouer(Joueur joueur, boolean pasDeferraille) {
        if (!pasDeferraille){
            joueur.piocherFeraille(1);
        }
        List<String> choixPossibles = new ArrayList<>();
        ArrayList<Tuile> tuiles = joueur.getCoordonnees();
        ArrayList<Tuile> tuilesPosables = new ArrayList<>();
        ArrayList<Tuile> tuilesVoisines = null;
        for (Tuile t : tuiles) {
            tuilesVoisines = t.getVoisines();
        }
        //assert tuilesVoisines != null;
        if (tuilesVoisines != null){
            tuiles.addAll(tuilesVoisines);
            for (Tuile t : tuiles){
                if (t.estPosable()){
                    tuilesPosables.add(t);
                }
            }

            for (int i = 0; i < joueur.getJeu().getTuiles().size(); i++) {
                if (tuilesPosables.contains(joueur.getJeu().getTuiles().get(i))) {
                    choixPossibles.add("TUILE:" + i);
                }
            }
        }


        if (tuiles.isEmpty()) { // alors c'est un test :c
            for (int i = 0; i < joueur.getJeu().getTuiles().size(); i++) {
                if (joueur.getJeu().getTuiles().get(i).estConstructible()) {
                    choixPossibles.add("TUILE:" + i);
                }
            }
        }

        String choix = joueur.choisir("Choisit une tuile sur laquelle mettre ta gare", choixPossibles, null, true);
        String[] words = choix.split(":");
        int index;
        if (words.length > 1){
            index = isNumeric(words[1]);
            if (index > -1){
                Tuile tuile = joueur.getJeu().getTuile(index);
                tuile.ajouterGare();
            }
        }
    }
}