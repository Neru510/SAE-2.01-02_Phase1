package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Depot extends Action {
    public Depot() {
        super("Dépôt", 3, 1);
    }
    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.log("Piochez 2 cartes. Défaussez 2 cartes de votre main");
        joueur.piocherEtAjouterMain(2);
        int i = 0;
        while (i < 2){
            List<String> choixPossibles = new ArrayList<>();
            for (Carte c : joueur.getMain()){
                choixPossibles.add(c.getNom());
            }
            String choix = joueur.choisir("", choixPossibles, null, true);
            Carte carte = joueur.getMain().retirer(choix);
            joueur.defausser(carte);
            i++;
        }

    }
}
