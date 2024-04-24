package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class BureauDuChefDeGare extends Action {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 4, 0);
    }
    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c: joueur.getMain()) {
            // ajoute les noms de toutes les cartes en main
            if (c.getType().equals("Action")){
                choixPossibles.add(c.getNom());
            }
        }

        if (!choixPossibles.isEmpty()){
            String choix = joueur.choisir("Choisissez une carte action que vous avez en main. Cette carte copie l'effet de la carte choisie.", choixPossibles, null, false);

            Carte carte = joueur.getMain().getCarte(choix);
            if (carte != null){
                carte.jouer(joueur);
            }
        }
    }
}
