package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

public class ParcDAttractions extends Action {
    public ParcDAttractions() {
        super("Parc d'attractions", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.log("Recevez X pièces. X est égal à la valeur de vente d'une de vos cartes train en jeu.");
    }
}
