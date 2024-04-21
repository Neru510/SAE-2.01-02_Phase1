package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class AtelierDeMaintenance extends Action {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 5, 0, "Dévoilez une carte train de votre main. Recevez une carte identique à celle dévoilée (s'il en reste dans la réserve)");
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        if (ListeCartePourTypeContenueDansMain(joueur, "Train") != null) {
            ListeDeCartes main = new ListeDeCartes();
            main = joueur.getMain();
            List<String> choixPossibles = new ArrayList<>();
            for (Carte c: main) {
                if (c.getType() == "Train") {
                    choixPossibles.add(c.getNom());
                }
            }
            String choix = joueur.choisir("Choisissez une carte Train à dévoiler de votre main", choixPossibles, null, false);
            Carte carte = main.getCarte(choix);
            joueur.devoilerCarte(carte);
            Carte res = joueur.retirerDeLaReserve(choix);
            joueur.ajouterCarteAMain(res);
        }
        joueur.message("Vous ne possédez aucune carte Train");
    }
}
