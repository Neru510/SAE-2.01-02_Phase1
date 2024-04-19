package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Depot extends Action {
    public Depot() {
        super("Dépôt", 3, 1, "Piochez 2 cartes. Défaussez 2 cartes de votre main");
    }
    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.piocherEtAjouterMain(2);
        /*
        ArrayList<String> choixMain = new ArrayList<>();
        for (Carte carte : joueur.getMain()){
            choixMain.add(carte.getNom());
        }

        String carte1 = joueur.choisir("Choisi une première carte à défausser", choixMain, null, false);
        ListeDeCartes c1 = FabriqueListeDeCartes.creerListeDeCartes(carte1, 1);
        Carte pCarte = c1.get(0);
        joueur.removeCarte(pCarte);
        String carte2 = joueur.choisir("Choisi la seconde carte à défausser", choixMain, null, false);
        ListeDeCartes c2 = FabriqueListeDeCartes.creerListeDeCartes(carte2, 1);
        Carte sCarte = c1.get(0);
        joueur.removeCarte(sCarte);
        joueur.defausser(pCarte);
        joueur.defausser(sCarte);*/
    }
}
