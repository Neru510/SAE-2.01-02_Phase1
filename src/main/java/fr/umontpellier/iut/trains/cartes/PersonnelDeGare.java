package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.*;

public class PersonnelDeGare extends Action {
    public PersonnelDeGare() {
        super("Personnel de gare", 2, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();
        choixPossibles.add("argent");
        choixPossibles.add("piocher");
        choixPossibles.add("ferraille");

        List<Bouton> boutonList = new List<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<Bouton> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(Bouton bouton) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends Bouton> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends Bouton> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Bouton get(int index) {
                return null;
            }

            @Override
            public Bouton set(int index, Bouton element) {
                return null;
            }

            @Override
            public void add(int index, Bouton element) {

            }

            @Override
            public Bouton remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<Bouton> listIterator() {
                return null;
            }

            @Override
            public ListIterator<Bouton> listIterator(int index) {
                return null;
            }

            @Override
            public List<Bouton> subList(int fromIndex, int toIndex) {
                return null;
            }
        };

        Bouton argent = new Bouton("argent", "argent");
        Bouton piocher = new Bouton("piocher", "piocher");
        Bouton ferraille = new Bouton("ferraille", "ferraille");

        boutonList.add(argent);
        boutonList.add(piocher);
        boutonList.add(ferraille);

        boolean check = false;
        while (!check){
            String choix = joueur.choisir("Choisissez 1 parmi ces 3 options : Piochez 1 carte. Recevez 1 pièce d'argent. Remettez 1 ferraille sur la pile ferraille", choixPossibles, null, false);

            if (choix.equals("piocher")){
                joueur.piocherEtAjouterMain();
                check = true;
            }
            else if (choix.equals("argent")){
                joueur.ajouterArgent(1);
                check = true;
            }
            else {
                Carte carte = joueur.getMain().retirer("Ferraille");
                if (carte != null) {
                    joueur.getMain().remove(carte);
                    joueur.getJeu().ajouterReserve(carte);
                    check = true;
                } else {
                    joueur.log("Vous n'avez pas de carte ferraille dans votre main");
                }
            }
        }
    }
}
