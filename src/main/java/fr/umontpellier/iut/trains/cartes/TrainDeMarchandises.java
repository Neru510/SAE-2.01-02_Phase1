package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainDeMarchandises extends TrainAction {
    public TrainDeMarchandises() {
        super("Train de marchandises", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        Carte c = demandes(joueur);
        while (c!=null){
            joueur.ajouterArgent(1);
            c = demandes(joueur);
        }
    }

    private Carte demandes(Joueur joueur){
        List<String> choixPossibles = new ArrayList<>();
        for (Carte c : joueur.getMain()){
            if (c.getType().equals("Ferraille")){
                choixPossibles.add(c.getNom());
            }
        }
        String choix;
        if (choixPossibles.isEmpty()){
            choix = joueur.choisir("Vous n'avez plus de carte ferraille. Cliquez sur passer", choixPossibles, null, true);
        }
        else {
            choix = joueur.choisir("Remettez sur la pile ferraille autant de cartes ferraille que vous voulez de votre main. Recevez 1 pièce par carte ferraille remise.", choixPossibles, null, true);
        }
        Carte c = joueur.getMain().retirer(choix);
        if (c!=null){
            joueur.getJeu().ajouterReserve(c);
        }
        return c;
    }
}
