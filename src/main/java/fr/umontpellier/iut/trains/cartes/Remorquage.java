package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Remorquage extends Action {
    public Remorquage() {
        super("Remorquage", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();
        ListeDeCartes defausse = joueur.getDefausse();
        for (Carte c : defausse){
            if (c.getType().equals("Train")){
                choixPossibles.add(c.getNom());
            }
        }
        String choix;
        if (choixPossibles.isEmpty()) {
            choixPossibles.add("");
        }
        choix = joueur.choisir("Prenez une carte train de votre défausse et ajoutez-la à votre main.", choixPossibles, null, false);
        joueur.prendreDefausseEtAjouterMain(choix);
    }

}
