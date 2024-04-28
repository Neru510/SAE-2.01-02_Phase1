package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsineDeWagons extends Action {
    public UsineDeWagons() {
        super("Usine de wagons", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        List<Bouton> boutons = new ArrayList<>();
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c : joueur.getMain()){
            if (Objects.equals(c.getType(), "Train")){
                choixPossibles.add(c.getNom());
            }
        }

        if (!choixPossibles.isEmpty()){
            String choix = joueur.choisir("Écartez une carte de votre main.", choixPossibles, null, true);
            Carte carte = joueur.getMain().retirer(choix);
            int prix = carte.getvaleur()*3;
            joueur.getJeu().ecarterCarte(carte);
            choixPossibles.clear();
            //boolean check = false;
            for (String nomCarte: joueur.getJeu().getReserve().keySet()) {
                // ajoute les noms des cartes dans la réserve préfixés de "ACHAT:"
                if (Objects.equals(joueur.getJeu().voirLaReserve(nomCarte).getType(), "Train") && joueur.getJeu().voirLaReserve(nomCarte).getCout() <= prix){
                    choixPossibles.add("ACHAT:" + nomCarte);
                }
            }

            choix = joueur.choisir("Choisissez la carte que vous souhaitez acheter, elle doit coûter moins ou égal à " + prix, choixPossibles, null, false);
            String[] choixDecoupe = choix.split(":");
            Carte c = joueur.getJeu().prendreDansLaReserve(choixDecoupe[1]);
            if (c != null){
                joueur.ajouterMain(c);
            }
        }
        else {
            joueur.message("Vous ne possédez aucune carte Train");
        }
    }
}
