package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

public class TGV extends TrainAction {
    public TGV() {
        super("TGV",2, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        for (Carte c : joueur.getCartesEnJeu()){
            if (c.getNom().equals("Train omnibus")){
                joueur.ajouterArgent(1);
                break;
            }
        }
    }
}
