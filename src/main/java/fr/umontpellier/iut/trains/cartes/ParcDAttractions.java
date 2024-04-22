package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class ParcDAttractions extends Action {
    public ParcDAttractions() {
        super("Parc d'attractions", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();

        for (Carte c: joueur.getCartesEnJeu()) {
            // ajoute les noms de toutes les cartes en main
            if (c.getType().equals("Train")){
                choixPossibles.add(c.getNom());
            }
        }

        String choix = joueur.choisir("Recevez X pièces. X est égal à la valeur de vente d'une de vos cartes train en jeu.", choixPossibles, null, true);

        Carte carte = joueur.getCartesEnJeu().getCarte(choix);
        if (carte != null && carte.getType().equals("Train")){
            joueur.ajouterArgent(carte.getvaleur());
        }
    }
}
