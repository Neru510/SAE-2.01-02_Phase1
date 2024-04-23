package fr.umontpellier.iut.trains.plateau;

/**
 * Classe représentant une tuile plaine, fleuve ou montagne.
 */
public class TuileTerrain extends Tuile {
    /**
     * Type de terrain de la tuile ({@code PLAINE}, {@code FLEUVE} ou {@code MONTAGNE})
     */
    private TypeTerrain type;

    public TuileTerrain(TypeTerrain type) {
        super();
        this.type = type;
    }

    public boolean estPosable(){
        return true;
    }

    @Override
    public int surCout() {
        if (type == TypeTerrain.PLAINE){
            return 0;
        }
        else if (type == TypeTerrain.MONTAGNE){
            return 2;
        }
        else { // type = fleuve
            return 1;
        }
    }

    @Override
    public String getType() {
        if (type == TypeTerrain.PLAINE){
            return "Plaine";
        }
        else if (type == TypeTerrain.MONTAGNE){
            return "Montagne";
        }
        else { // type = fleuve
            return "Fleuve";
        }
    }
}
