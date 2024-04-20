package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

public class Echangeur extends Action {
    public Echangeur() {
        super("Échangeur", 3, 1);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        joueur.log("Remettez une carte train de votre zone de jeu sur le dessus de votre deck.");
    }
}
