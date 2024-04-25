package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.*;

public class PersonnelDeGare extends Action {
    public PersonnelDeGare() {
        super("Personnel de gare", 2, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();
        choixPossibles.add("argent");
        choixPossibles.add("piocher");
        choixPossibles.add("ferraille");

        List<Bouton> boutonList = new ArrayList<>();

        Bouton argent = new Bouton("Argent", "argent");
        Bouton piocher = new Bouton("Piocher", "piocher");
        Bouton ferraille = new Bouton("Ferraille", "ferraille");

        boutonList.add(argent);
        boutonList.add(piocher);
        boutonList.add(ferraille);

        boolean check = false;
        while (!check){
            String choix = joueur.choisir("Choisissez 1 parmi ces 3 options : Piochez 1 carte. Recevez 1 pièce d'argent. Remettez 1 ferraille sur la pile ferraille", choixPossibles, boutonList, true);

            if (choix.equals("piocher")){
                joueur.piocherEtAjouterMain();
                check = true;
            }
            else if (choix.equals("argent")){
                joueur.ajouterArgent(1);
                check = true;
            }
            else {
                Carte carte = joueur.getMain().retirer("Ferraille");
                if (carte != null) {
                    joueur.getMain().remove(carte);
                    joueur.getJeu().ajouterReserve(carte);
                    check = true;
                } else {
                    joueur.log("Vous n'avez pas de carte ferraille dans votre main");
                }
            }
        }
    }
}
