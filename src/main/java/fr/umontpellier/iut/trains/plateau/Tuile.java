package fr.umontpellier.iut.trains.plateau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Tuile {
    /**
     * Liste des tuiles voisines qui sont connectées à la tuile courante (voisines
     * sur le plateau, sauf les tuiles entre lesquelles il y a une barrière
     * infranchissable)
     */
    private ArrayList<Tuile> voisines;
    /**
     * Ensemble des joueurs qui ont posé un rail sur la tuile
     */
    private Set<Joueur> rails;

    private int nbRails;

    public Tuile() {
        this.voisines = new ArrayList<>();
        this.rails = new HashSet<>();
        nbRails = 0;
    }

    /**
     * @return {@code true} si la tuile ne contient aucun rail, {@code false} sinon
     */
    public boolean estVide() {
        return rails.isEmpty();
    }

    public void ajouterGare(){

    }

    /**
     * @param joueur le joueur dont on veut déterminer s'il a posé un rail sur la
     *               tuile
     * @return {@code true} si le joueur a posé un rail sur la tuile, {@code false}
     *         sinon
     */
    public boolean hasRail(Joueur joueur) {
        return rails.contains(joueur);
    }

    /**
     * Ajoute un rail du joueur sur la tuile
     * 
     * @param joueur le joueur qui pose un rail sur la tuile
     */
    public void ajouterRail(Joueur joueur) {
        joueur.ajouterNbJetonsRails(-1);
        rails.add(joueur);
        nbRails++;
    }

    /**
     * Ajoute une voisine à la tuile courante, et ajoute la tuile courante comme
     * voisine de la tuile passée en argument.
     * <p>
     * Cette méthode est appelée par la méthode {@code Plateau.makeTuiles()} pour
     * construire le plateau de jeu.
     * 
     * @param tuile la tuile voisine à ajouter
     */
    public void ajouterVoisine(Tuile tuile) {
        voisines.add(tuile);
        tuile.voisines.add(this);
    }

    public ArrayList<Tuile> getVoisines(){
        return voisines;
    }

    /**
     * Supprime une tuile de la liste de voisines de {@code this} (et supprime
     * {@code this} des voisines de la tuile passée en paramètres).
     * <p>
     * Cette méthode est appelée par la méthode {@code Plateau.makeTuiles()} pour
     * représenter les barrières infranchissables sur le plateau.
     * 
     * @param tuile la tuile voisine à supprimer
     */
    public void supprimerVoisine(Tuile tuile) {
        voisines.remove(tuile);
        tuile.voisines.remove(this);
    }

    /**
     * @return le nombre de jetons gare posés sur la tuile. Par défaut la fonction
     *         renvoie 0 car on ne peut pas poser de jeton gare sur une tuile
     *         quelconque.
     */
    public int getNbGares() {
        return 0;
    }

    /**
     * @return une représentation de la tuile sous la forme d'un dictionnaire de
     *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     *         l'interface
     *         graphique)
     */
    public Map<String, Object> dataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("rails", rails.stream().map(Joueur::getCouleur).toArray());
        int nbGares = getNbGares();
        if (nbGares > 0) {
            map.put("nbGares", nbGares);
        }
        return map;
    }

    public abstract boolean estPosable(); // utilisé pour savoir si les rails peuvent être construite dessus

    public boolean estConstructible(){ // utilisé pour que le joueur choisisse sa position de départ
        return true;
    }
    public boolean peutPoserGare(){
        return false;
    }

    public abstract int surCout();

    public abstract String getType();

    public int getNbRails(){
        return nbRails;
    }
}
