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
        if (mainContientCarte(joueur, "Train")) {
            joueur.choisir("Dévoilez une carte Train de votre main. Pour recevoir une carte identique à celle dévoilée (s'il en reste dans la réserve).", )
            ListeDeCartes main, cartesEnJeu, cartesRecues = new ListeDeCartes();
            main = joueur.getMain();
            cartesEnJeu = joueur.getCartesEnJeu();
            cartesRecues = joueur.getCartesRecues();
            List<String> choixPossibles = new ArrayList<>();
            for (Carte c: main) {
                if (c instanceof Train) {
                    choixPossibles.add(c.getNom());
                }
            }
            String choix = joueur.choisir("Choisisez une carte train de votre mains", choixPossibles, null, false);
            Carte carte = main.retirer(choix);
            joueur.setMain(main);
        }


    }

}
