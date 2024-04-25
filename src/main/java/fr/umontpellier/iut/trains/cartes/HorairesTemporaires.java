package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class HorairesTemporaires extends Action {
    public HorairesTemporaires() {
        super("Horaires temporaires", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        int i = 0;
        while (i == 2 || joueur.getPioche().isEmpty()) {
            Carte carte = joueur.piocher();
            joueur.devoilerCarte(carte);
            if (carte.getType().equals("Train")){
                i++;
            }
            else {
                joueur.removeCarte(carte);
                joueur.defausser(carte);
            }
        }
    }
}
