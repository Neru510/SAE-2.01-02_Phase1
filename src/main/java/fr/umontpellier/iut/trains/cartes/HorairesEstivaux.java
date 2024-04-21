package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import fr.umontpellier.iut.trains.Bouton;

import java.util.*;

public class HorairesEstivaux extends Action {
    public HorairesEstivaux() {
        super("Horaires estivaux", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        super.jouer(joueur);
        List<String> choixPossibles = new ArrayList<>();
        choixPossibles.add("oui");
        choixPossibles.add("non");
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
        Bouton oui = new Bouton("oui", "oui");
        Bouton non = new Bouton("non", "non");
        boutonList.add(oui);
        boutonList.add(non);
        String choix = joueur.choisir("Vous pouvez écarter cette carte. Dans ce cas, recevez 3 pièces.", choixPossibles, null, false);
        if (choix.equals("oui")){
            joueur.getJeu().ecarterCarte(joueur.getCartesEnJeu().retirer(this.getNom()));
            joueur.ajouterArgent(3);
        }
    }
}
