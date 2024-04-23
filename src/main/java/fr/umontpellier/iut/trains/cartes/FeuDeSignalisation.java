package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.Bouton;

import java.util.ArrayList;
import java.util.List;

public class FeuDeSignalisation extends Action {
    public FeuDeSignalisation() {
        super("Feu de signalisation", 2, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.piocherEtAjouterMain();
        List<String> choixPossibles = choixOuiNon();
        List<Bouton> boutonList = choixOuiNonBouton();
        String choix = joueur.choisir("Consultez la première carte de votre deck. Défaussez-la ou replacez-la sur le dessus de votre deck.", choixPossibles, boutonList, true);
        if (choix.equals("oui")){
            Carte c = joueur.piocher();
            joueur.defausser(c);
        }
    }
}
