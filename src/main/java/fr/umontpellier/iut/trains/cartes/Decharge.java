package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;
import java.util.List;

public class Decharge extends Action {
    public Decharge() {
        super("Décharge", 2, 0);
    }

    public void jouer(Joueur joueur){
        List<Carte> cartes = joueur.prendreCarteDeMain("Ferraille");
        joueur.getJeu().ajouterReserve(cartes);
    }
}
