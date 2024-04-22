package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class CentreDeControle extends Action {
    public CentreDeControle() {
        super("Centre de contrôle", 3, 0, "Piochez 1 carte puis nommez une carte. Dévoilez la première carte de votre deck. Si c'est la carte nommée, ajoutez-la à votre main. Sinon, remettez-la sur votre deck.");
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.piocher();
        String nomCarte = joueur.lireLigne();
        Carte premiereCartePioche = joueur.getPioche().get(0);
        joueur.devoilerCarte(premiereCartePioche);
        if (premiereCartePioche.getNom().equals(nomCarte)) {
            joueur.ajouterMain(premiereCartePioche);
        } else {
            joueur.ajouterAuDessusDeLaPioche(premiereCartePioche);
        }
    }
}
