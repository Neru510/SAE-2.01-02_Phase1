package fr.umontpellier.iut.trains.plateau;

/**
 * Classe représentant une tuile ville (où l'on peut poser des gares)
 */
public class TuileVille extends Tuile {
    /**
     * Nombre maximum de gares que l'on peut poser sur la tuile
     */
    private int nbGaresMax;
    /**
     * Nombre de gares posées sur la tuile
     */
    private int nbGaresPosees;

    public TuileVille(int taille) {
        super();
        this.nbGaresMax = taille;
        this.nbGaresPosees = 0;
    }

    public boolean estPosable(){
        return true;
    }

    @Override
    public boolean peutPoserGare(){
        return true;
    }

    @Override
    public int surCout() {
        return 1 + nbGaresPosees;
    }

    @Override
    public String getType() {
        return "Ville";
    }

    public int getNbGaresMax(){
        return nbGaresMax;
    }

    @Override
    public void ajouterGare(){
        if (nbGaresPosees+1 <= nbGaresMax){
            nbGaresPosees++;
        }
    }

    @Override
    public int getNbGares(){
        return nbGaresPosees;
    }
}
