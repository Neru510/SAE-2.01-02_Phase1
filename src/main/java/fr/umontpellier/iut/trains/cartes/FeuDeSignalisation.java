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
        ArrayList<Bouton> boutonList = new ArrayList<>();
        Bouton piocher = new Bouton("Pioche", "non");
        Bouton defausser = new Bouton("Défausse", "oui");
        boutonList.add(piocher);
        boutonList.add(defausser);
        Carte c;
        if (joueur.getPioche().isEmpty()){
            joueur.getDefausse().melanger();
            joueur.getPioche().addAll(joueur.getDefausse());
            joueur.getDefausse().clear();
        }
        else {
            c = joueur.getPioche().get(0);
            joueur.devoilerCarte(c);
        }

        String choix = joueur.choisir("Défaussez la carte ou replacez-la sur le dessus de votre deck.", choixOuiNon(), boutonList, true);

        if (choix.equals("non")){
            c = joueur.getPioche().retirer(joueur.getPioche().get(0));
            joueur.ajouterAuDessusDeLaPioche(c);
        }
        else {
            c = joueur.getPioche().retirer(joueur.getPioche().get(0));
            joueur.defausser(c);
        }
    }
}
