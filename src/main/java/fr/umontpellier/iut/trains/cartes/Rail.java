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
    public void jouer(Joueur joueur, boolean enleveSurcout, boolean enleveSurcoutMontagne, boolean enleveSurcoutVille, boolean enleveSurcoutRiviere, boolean enleveSurcoutJoueurs, boolean enleveCarteFerraille) {
        super.jouer(joueur);
        Carte carte;

        if (!enleveCarteFerraille){
            carte = joueur.getJeu().prendreDansLaReserve("Ferraille");
            joueur.ajouterCartesRecues(carte);
        }
        joueur.ajouterPointsRails(1);
        List<String> choixPossibles = new ArrayList<>();
        ArrayList<Tuile> tuiles = joueur.getCoordonnees();
        ArrayList<Tuile> tuilesPosables = new ArrayList<>();
        for (Tuile t : tuiles) {
            if(t.estConstructible()){
                tuilesPosables.add(t);
            }
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

        Tuile t = null;
        boolean check = false;
        String choix = "";
        String[] words;

        if (!tuilesPosables.isEmpty()) {
            while (!check) {
                t = null;
                while (t == null) {
                    choix = joueur.choisir("Choisissez une tuile sur laquelle mettre vos rails", choixPossibles, null, true);
                    if (choix.isEmpty()){
                        break;
                    }
                    words = choix.split(":");
                    if (isNumeric(words[1]) > -1) {
                        t = joueur.getJeu().getTuile(isNumeric(words[1]));
                    }
                }
                if (choix.isEmpty()){
                    break;
                }
                int surcout = 0;

                if (enleveSurcout){ // enlève le surcout total
                    check = true;
                }
                else if (enleveSurcoutJoueurs && (Objects.equals(t.getType(), "Ville") && enleveSurcoutVille) || (Objects.equals(t.getType(), "Fleuve") && enleveSurcoutRiviere) || (Objects.equals(t.getType(), "Montagne") && enleveSurcoutMontagne)){

                }
                else if ((Objects.equals(t.getType(), "Ville") && enleveSurcoutVille) || (Objects.equals(t.getType(), "Fleuve") && enleveSurcoutRiviere) || (Objects.equals(t.getType(), "Montagne") && enleveSurcoutMontagne)){
                    surcout += t.getNbRails();
                    if (t.getNbRails() > 0){
                        carte = joueur.getJeu().prendreDansLaReserve("Ferraille");
                        joueur.ajouterCartesRecues(carte);
                    }
                }
                else if (enleveSurcoutJoueurs){
                    surcout += t.surCout();
                    surcout += t.getNbGares();
                }
                else {
                    surcout += t.getNbRails() + t.surCout() + t.getNbGares();
                }

                if (!enleveSurcoutJoueurs && t.getNbRails() > 0){
                    carte = joueur.getJeu().prendreDansLaReserve("Ferraille");
                    joueur.ajouterCartesRecues(carte);
                }

                if (surcout <= joueur.getArgent()){
                    joueur.ajouterArgent(-surcout);
                    check = true;
                }
                else {
                    int a = surcout - joueur.getArgent();
                    joueur.message("Il manque " + a + " pièces");
                }
            }
            if (t!= null && t.getType().equals("Etoile")){
                joueur.ajouterPointScoreTotal(t.surCout());
            }

            if (t != null){
                t.ajouterRail(joueur);
                joueur.ajouterCoordonnees(t);
            }
        }
    }
}
