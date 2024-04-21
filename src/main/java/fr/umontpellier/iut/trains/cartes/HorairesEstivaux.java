package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import fr.umontpellier.iut.trains.Bouton;

import java.util.*;

public class HorairesEstivaux extends Action {
    public HorairesEstivaux() {
        super("Horaires estivaux", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        List<String> choixPossibles = choixOuiNon();
        List<Bouton> boutonList = choixOuiNonBouton();
        String choix = joueur.choisir("Vous pouvez écarter cette carte. Dans ce cas, recevez 3 pièces.", choixPossibles, null, false);
        if (choix.equals("oui")){
            joueur.getJeu().ecarterCarte(joueur.getCartesEnJeu().retirer(this.getNom()));
            joueur.ajouterArgent(3);
        }
    }
}
