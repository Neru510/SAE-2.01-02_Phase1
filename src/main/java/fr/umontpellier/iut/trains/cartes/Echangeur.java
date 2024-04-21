package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Echangeur extends Action {
    public Echangeur() {
        super("Échangeur", 3, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);

        List<String> choixPossibles = new ArrayList<>();

        for (Carte c : joueur.getCartesEnJeu()){
            if (c.getType().equals("Train")){
                choixPossibles.add(c.getNom());
            }
        }

        String choix = joueur.choisir("Remettez une carte train de votre zone de jeu sur le dessus de votre deck.", choixPossibles, null, false);

        Carte c = joueur.getCartesEnJeu().getCarte(choix);
        if (c != null ){
            c = joueur.getCartesEnJeu().retirer(choix);
            joueur.getPioche().add(0,c);
        }
    }
}
