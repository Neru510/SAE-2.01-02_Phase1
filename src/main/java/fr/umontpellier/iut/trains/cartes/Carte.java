package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Carte {
    private final String nom;
    private final int cout;
    private final int valeur;
    private final String type;
    private final String couleur;
    private int nbPrestige = 0;
    private boolean ferraille = false;

    /**
     * Constructeur simple
     * <p>
     * Important : La classe Carte est actuellement très incomplète. Vous devrez
     * ajouter des attributs et des méthodes et donc probablement définir au moins
     * un autre constructeur plus complet. Les sous-classes de Cartes qui vous sont
     * fournies font appel à ce constructeur simple mais au fur et à mesure que vous
     * les compléterez, elles devront utiliser les autres constructeurs de Carte. Si
     * vous n'utilisez plus ce constructeur, vous pouvez le supprimer.
     * 
     * @param nom
     */
    public Carte(String nom, String type, String couleur, int cout, int valeur) {
        this.nom = nom;
        this.type = type;
        this.couleur = couleur;
        this.cout = cout;
        this.valeur = valeur;
    }
    public Carte(String nom, String type, String couleur, int cout, int valeur, int nbPrestige) {
        this.nom = nom;
        this.couleur = couleur;
        this.cout = cout;
        this.valeur = valeur;
        this.type = type;
        this.nbPrestige = nbPrestige;
    }

    public Carte(String nom, String type, String couleur){
        this.nom = nom;
        this.type = type;
        this.couleur = couleur;
        this.cout = 0;
        this.valeur = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getCout() {
        return cout;
    }

    public int getvaleur(){
        return valeur;
    }

    public String getType(){
        return type;
    }

    public String getCouleur(){
        return couleur;
    }
    
    public int getNbPrestige(){
        return nbPrestige;
    }

    /**
     * Cette fonction est exécutée lorsqu'un joueur joue la carte pendant son tour.
     * Toutes les cartes ont une méthode jouer, mais elle ne fait rien par défaut.
     * 
     * @param joueur le joueur qui joue la carte
     */
    public void jouer(Joueur joueur) {
        if (this.valeur > 0) {
            int argentDepart = joueur.getArgent();
            joueur.setArgent(joueur.getArgent() + valeur);
        }
        if (this.nbPrestige > 0){
            joueur.ajouterPointScoreTotal(nbPrestige);
        }
        // On rajoute
    }

    public boolean mainContientCarte(Joueur joueur, String type) {
        for (Carte carte : joueur.getMain()) {
            if (carte.getType() == type) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return nom;
    }

    public boolean isFeraille(){
        return ferraille;
    }

    public void setFeraille(boolean ferraille){
        this.ferraille=ferraille;
    }
}
