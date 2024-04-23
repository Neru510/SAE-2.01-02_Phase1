package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.Tuile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PoseDeRails extends Rail {
    public PoseDeRails() {
        super("Pose de rails", 3);
    }

    @Override
    public void jouer(Joueur joueur, boolean enleveSurcout, boolean enleveSurcoutMontagne, boolean enleveSurcoutVille, boolean enleveSurcoutRiviere) {
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();
        ArrayList<Tuile> tuiles = joueur.getCoordonnees();
        ArrayList<Tuile> tuilesPosables = new ArrayList<>();
        for (Tuile t : tuiles) {
            ArrayList<Tuile> tuilesVoisines = t.getVoisines();
            for (Tuile tt : tuilesVoisines) {
                if (tt.estConstructible()) {
                    tuilesPosables.add(tt);
                }
            }
        }

        for (int i = 0; i < joueur.getJeu().getTuiles().size(); i++){
            if (tuilesPosables.contains(joueur.getJeu().getTuiles().get(i))){
                choixPossibles.add("TUILE:" + i);
            }
        }

        String choix = joueur.choisir("Choisissez une tuile sur laquelle mettre vos rails", choixPossibles, null, true);
        String[] words = choix.split(":");
        Tuile t = null;
        boolean check = false;
        if (Integer.parseInt(words[1]) > -1) {
            t = joueur.getJeu().getTuile(Integer.parseInt(words[1]));
        }

        while (!check) {
            while (t == null) {
                choix = joueur.choisir("Choisissez une tuile sur laquelle mettre vos rails", choixPossibles, null, true);
                if (choix.isEmpty()){
                    break;
                }
                words = choix.split(":");
                if (Integer.parseInt(words[1]) > -1) {
                    t = joueur.getJeu().getTuile(Integer.parseInt(words[1]));
                }
            }
            if (choix.isEmpty()){
                break;
            }
            check = true;

            if (Objects.equals(t.getType(), "Etoile")) {
                check = surcout(joueur, enleveSurcout, t);
            } else if (Objects.equals(t.getType(), "Ville")) {
                check = surcout(joueur, enleveSurcoutRiviere, t);
            } else if (Objects.equals(t.getType(), "Fleuve")) {
                check = surcout(joueur, enleveSurcoutRiviere, t);
            } else if (Objects.equals(t.getType(), "Montagne")) {
                check = surcout(joueur, enleveSurcoutMontagne, t);
            }
            t.ajouterRail(joueur);
        }
        joueur.ajouterCoordonnees(t);
    }

    public boolean surcout(Joueur joueur, boolean pasSurcout, Tuile t) {
        if (!pasSurcout && t.surCout() <= joueur.getArgent()) {
            joueur.ajouterArgent(-(t.surCout() + t.getNbRails()));
            t.ajouterRail(joueur);
            return true;
        }
        else if (t.getNbRails() <= joueur.getArgent()){
            joueur.ajouterArgent(-t.getNbRails());
            t.ajouterRail(joueur);
        }
        return pasSurcout;
    }
}
