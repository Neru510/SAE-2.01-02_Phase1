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
        int tailleReel = 0;
        ListeDeCartes devoiler = new ListeDeCartes();
        List<Bouton> boutons = new ArrayList<>();
        int taille = joueur.getPioche().size() + joueur.getDefausse().size();
        if (taille >= 4) {
            tailleReel = 4;
        } else {
            tailleReel = taille;
        }

        devoiler = (ListeDeCartes) joueur.piocher(tailleReel);
        int i = 1;
        for (Carte c : devoiler) {
            boutons.add(new Bouton(c.getNom(), c.getNom()));
            i++;
        }
        joueur.devoilerCartes(devoiler);

        String choix = joueur.choisir("Choisisez une carte a prendre ou passer", null, boutons, true);
        Carte carte = devoiler.getCarte(choix);

        if (carte != null) {
            joueur.ajouterMain(carte);
            devoiler.retirer(carte.getNom());
            joueur.message("Carte " + carte.getNom() + " ajouter a votre main");
        } else joueur.message("Aucune carte ajouter a votre main");

        for (int a = 0; a < devoiler.size();) {
            for (Carte c : devoiler) {
                boutons.add(new Bouton(c.getNom(), c.getNom()));
                i++;
            }
            joueur.devoilerCartes(devoiler);

            choix = joueur.choisir("Choisisez la carte a remettre sur la pioche", null, boutons, false);
            joueur.ajouterAuDessusDeLaPioche(devoiler.retirer(choix));
        }
    }
}
