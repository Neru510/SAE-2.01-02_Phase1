package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CabineDuConducteur extends Action {
    public CabineDuConducteur() {
        super("Cabine du conducteur", 2, 0, "Défaussez autant de cartes que vous voulez de votre main. Piochez 1 carte par carte défaussée.");
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        boolean finit = true;
        int i = 0;
        ListeDeCartes main = new ListeDeCartes();
        List<String> choixPossibles = new ArrayList<>();
        main = joueur.getMain();
        for (Carte c: main) {
            choixPossibles.add(c.getNom());
        }
        while (finit) {
            String choix = joueur.choisir("Choisissez les carte à défaussez vous avez en main et envoyer un champ vide pour finir", choixPossibles, null, true);
            if (!(choix.equals(""))){
                i++;
                Carte res = main.retirer(choix);
                joueur.defausser(res);
                choixPossibles.remove(choix);
            } else finit = false;
        }
        joueur.piocherEtAjouterMain(i);
    }
}
