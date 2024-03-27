package fr.umontpellier.iut.trains.cartes;

public class CentreDeControle extends Action {
    public CentreDeControle() {
        super("Centre de contrôle", 3, 0, "Piochez1 carte puis nommez une carte. Dévoilez la première carte de votre deck. Si c'est la carte nommée, ajoutez-la à votre main. Sinon, remettez-la sur votre deck.");
    }
}
