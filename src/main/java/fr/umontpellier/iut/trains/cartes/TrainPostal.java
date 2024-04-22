package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainPostal extends TrainAction {
    public TrainPostal() {
        super("Train postal", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);

        Carte c = demandes(joueur);
        while (c!=null){
            joueur.defausser(c);
            joueur.ajouterArgent(1);
            c = demandes(joueur);
        }
    }

    private Carte demandes(Joueur joueur){
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c : joueur.getMain()){
            choixPossibles.add(c.getNom());
        }
        String choix = joueur.choisir("Défaussez autant de cartes que vous voulez de votre main. Recevez 1 pièce par carte défaussée.", choixPossibles, null, true);
        Carte c = joueur.getMain().retirer(choix);
        return c;
    }

}
