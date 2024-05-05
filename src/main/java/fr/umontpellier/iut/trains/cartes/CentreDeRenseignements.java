package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeRenseignements extends Action {
    public CentreDeRenseignements() {
        super("Centre de renseignements", 4, 1, "Dévoilez les 4 premières cartes de votre deck. Vous pouvez en prendre 1 dans votre main. Remettez les autres sur le dessus de votre deck dans l'ordre de votre choix.");
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        int tailleReel;
        ListeDeCartes devoiler;
        List<Bouton> boutons = new ArrayList<>();
        int taille = joueur.getPioche().size() + joueur.getDefausse().size();
        if (taille >= 4) {
            tailleReel = 4;
        } else {
            tailleReel = taille;
        }

        devoiler = (ListeDeCartes) joueur.piocher(tailleReel);
        for (Carte c : devoiler) {
            boutons.add(new Bouton(c.getNom(), c.getNom()));
        }
        joueur.devoilerCartes(devoiler);

        String choix = joueur.choisir("Choisissez une carte a prendre", null, boutons, true);
        Carte carte = devoiler.getCarte(choix);

        if (carte != null) {
            joueur.ajouterMain(carte);
            devoiler.retirer(carte.getNom());
            joueur.message("Carte " + carte.getNom() + " ajoutée à votre main");
        }
        for (Carte c : devoiler) {
            boutons.add(new Bouton(c.getNom(), c.getNom()));
        }

        while(!devoiler.isEmpty()) {
            joueur.devoilerCartes(devoiler);
            boutons.clear();
            for (Carte c : devoiler) {
                boutons.add(new Bouton(c.getNom(), c.getNom()));
            }
            choix = joueur.choisir("Choisissez les cartes dans l'ordre à remettre sur la pioche", null, boutons, false);
            Carte c = devoiler.retirer(choix);
            joueur.ajouterAuDessusDeLaPioche(c);
        }

    }
}
