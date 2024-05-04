package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AtelierDeMaintenance extends Action {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        if (ListeCartePourTypeContenueDansMain(joueur, "Train") != null) {
            List<String> choixPossibles = new ArrayList<>();
            for (Carte c: joueur.getMain()) {
                if (Objects.equals(c.getType(), "Train")) {
                    choixPossibles.add(c.getNom());
                }
            }
            String choix = joueur.choisir("Choisissez une carte train à dévoiler de votre main", choixPossibles, null, false);
            Carte carte = joueur.getMain().getCarte(choix);
            joueur.devoilerCarte(carte);
            Carte res = joueur.retirerDeLaReserve(choix);
            if (res!= null){
                joueur.ajouterCartesRecues(res);
            }
            else {
                joueur.message("Vous n'avez pas de carte " + carte.toString() + " dans la réserve");
            }
        }
        else {
            joueur.choisir("Vous ne possédez aucune carte train. Cliquez sur passer", null, null, true);
        }
    }
}
