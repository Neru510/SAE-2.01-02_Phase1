package fr.umontpellier.iut.trains.plateau;

/**
 * Classe représentant une tuile étoile (lieu éloigné)
 */
public class TuileEtoile extends Tuile {
    /**
     * Valeur du lieu éloigné (valeur indiquée sur le plateau)
     */
    private int valeur;

    public TuileEtoile(int valeur) {
        super();
        this.valeur = valeur;
    }

    public boolean estPosable(){
        return true;
    }

    public boolean estConstructible(){
        return false;
    }

    @Override
    public int surCout() {
        return valeur;
    }

    @Override
    public String getType() {
        return "Etoile";
    }
}
