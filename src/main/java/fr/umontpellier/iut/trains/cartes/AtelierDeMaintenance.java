package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import java.util.ArrayList;
import java.util.List;

public class AtelierDeMaintenance extends Action {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 5, 0, "Dévoilez une carte train de votre main. Recevez une carte identique à celle dévoilée (s'il en reste dans la réserve)");
    }
    /*
    @Override
    public void jouer(Joueur joueur) {

        super.jouer(joueur);
        ListeDeCartes main, cartesEnJeu, cartesRecues = new ListeDeCartes();
        main = joueur.getMain();
        cartesEnJeu = joueur.getCartesEnJeu();
        cartesRecues = joueur.getCartesRecues();
        if (contientCarteTrain(joueur)){
            List<String> choixPossibles = new ArrayList<>();
            for (Carte c: main) {
                if (c instanceof Train) {
                    choixPossibles.add(c.getNom());
                }
            }
            String choix = joueur.choisir("Choisisez une carte train de votre mains", choixPossibles, null, false);
            Carte carte = main.retirer(choix);
            joueur.setMain(main);

        } else;

    }

    public boolean contientCarteTrain(Joueur joueur) {
        for (Carte carte : joueur.getMain()) {
            if (carte instanceof Train) {
                return true;
            }
        }
        return false;
    }
    */
}
