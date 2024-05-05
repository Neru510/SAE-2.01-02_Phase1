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
        ListeDeCartes res = new ListeDeCartes();

        while (((!joueur.getPioche().isEmpty()) || (!joueur.getDefausse().isEmpty())) && i < 2) {
            Carte carte = joueur.piocherEtAjouterMain();
            joueur.devoilerCarte(carte);
            if (carte.getType().contains("Train")) {
                i++;
            } else {
                res.add(carte);
                joueur.removeCarte(carte);
            }
        }
        joueur.message(i +" carte de type train trouver");
        joueur.defausser(res);
    }
}
