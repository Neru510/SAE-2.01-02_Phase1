package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.awt.*;
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
        ArrayList<Bouton> buttons = new ArrayList<>();
        for (Carte c : defausse){
            if (c.getType().contains("Train")){
                buttons.add(new Bouton(c.getNom()));
                choixPossibles.add(c.getNom());
            }
        }
        if (!buttons.isEmpty()){
            String choix = joueur.choisir("Prenez une carte train de votre défausse et ajoutez-la à votre main.", choixPossibles, buttons, false);
            joueur.prendreDefausseEtAjouterMain(choix);
        }
        else {
            joueur.choisir("Prenez une carte train de votre défausse et ajoutez-la à votre main.", null, null, true);
        }
    }

}
