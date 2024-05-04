package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class BureauDuChefDeGare extends Action {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 4, 0);
    }
    @Override
    public int jouerBureau(Joueur joueur, List<String> choixChoisis){
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c: joueur.getMain()) {
            // ajoute les noms de toutes les cartes en main
            if (c.getType().contains("Action") && !c.equals(this)){
                choixPossibles.add(c.getNom());
            }

        }

        if (!choixPossibles.isEmpty()){
            String choix = joueur.choisir("Choisissez une carte action que vous avez en main. Cette carte copie l'effet de la carte choisie.", choixPossibles, null, false);
            joueur.getCartesEnJeu().add(this);
            while(choix.equals("Bureau du chef de gare")){
                choixPossibles.remove(joueur.getMain().getCarte(choix));
                choix = joueur.choisir("Choisissez une carte action que vous avez en main. Cette carte copie l'effet de la carte choisie.", choixPossibles, null, true);
            }

            Carte carte = joueur.getMain().getCarte(choix);
            if (carte != null){
                choixPossibles.add(choix);
                if (carte.getNom().equals("Train matinal")){
                    carte.jouer(joueur, true);
                    return 1;
                }
                else if (carte.getNom().equals("Dépotoir")){
                    carte.jouer(joueur, true);
                    return -1;
                }
                else if (carte.getNom().equals("Horaires estivaux")){
                    carte.jouer(joueur, true);
                }
                else if (carte.getNom().equals("Ferronnerie")){
                    return -2;
                }
                else {
                    carte.jouer(joueur, true);
                }
            }
        }
        else {
            joueur.choisir("Vous n'avez pas de carte action. Cliquez sur passer", null, null, true);
        }


        return 0;
    }
}
