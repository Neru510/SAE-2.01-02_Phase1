package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeControle extends Action {
    public CentreDeControle() {
        super("Centre de contrôle", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.piocherEtAjouterMain();
        ListeDeCartes choixPossibles = joueur.getToutLesCarteEnJeu();
        List<Bouton> boutons = new ArrayList<>();
        for (Carte carte : choixPossibles){
            boutons.add(new Bouton(carte.getNom()));
        }

        String choix = joueur.choisir("Choisissez une carte qui pourrait être le première carte de la pioche.", null, boutons, false);

        Carte carte = choixPossibles.getCarte(choix);
        Carte cartePioche = joueur.getPioche().get(0);
        joueur.devoilerCarte(cartePioche);
        if (cartePioche.getNom().equals(carte.getNom())){
            joueur.message("Gagné, vous gardez la carte");
            joueur.piocherEtAjouterMain();
        } else {
            joueur.message("Perdu...");
        }
    }
}
