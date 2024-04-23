package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeControle extends Action {
    public CentreDeControle() {
        super("Centre de contrôle", 3, 0, "Piochez 1 carte puis nommez une carte. Dévoilez la première carte de votre deck. Si c'est la carte nommée, ajoutez-la à votre main. Sinon, remettez-la sur votre deck.");
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.piocherEtAjouterMain();

        joueur.message("Nommez une carte");
        String nomCarte = joueur.lireLigne();

        Carte premiereCartePioche = joueur.getPioche().get(0);

        joueur.devoilerCarte(premiereCartePioche);

        if (premiereCartePioche.getNom().equals(nomCarte)) {
            joueur.message("Gagné, vous garder la carte");
            joueur.piocherEtAjouterMain();
        } else {
            joueur.message("Perdu, vous la remettez au dessus du paquet");
        }
    }
}
