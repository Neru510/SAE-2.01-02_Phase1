package fr.umontpellier.iut.trains.cartes;

public class FeuDeSignalisation extends Action {
    public FeuDeSignalisation() {
        super("Feu de signalisation", 2, 0, "Piochez 1 carte puis consultez la première carte de votre deck. Défaussez-la ou replacez-la sur le dessus de votre deck.");
    }
}
