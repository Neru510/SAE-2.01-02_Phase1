package fr.umontpellier.iut.trains;

import java.util.*;

import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.FabriqueListeDeCartes;
import fr.umontpellier.iut.trains.cartes.ListeDeCartes;
import fr.umontpellier.iut.trains.plateau.Tuile;

import static fr.umontpellier.iut.trains.Jeu.isNumeric;

public class Joueur {
    /**
     * Le jeu auquel le joueur est rattaché
     */
    private Jeu jeu;
    /**
     * Nom du joueur (pour les affichages console et UI)
     */
    private String nom;
    /**
     * Quantité d'argent que le joueur a (remis à zéro entre les tours)
     */
    private int argent;
    /**
     * Nombre de points rails dont le joueur dispose. Ces points sont obtenus en
     * jouant les cartes RAIL (vertes) et remis à zéro entre les tours
     */
    private int pointsRails;
    /**
     * Nombre de jetons rails disponibles (non placés sur le plateau)
     */
    private int nbJetonsRails;
    /**
     * Liste des cartes en main
     */
    private ListeDeCartes main;
    /**
     * Liste des cartes dans la pioche (le début de la liste correspond au haut de
     * la pile)
     */
    private ListeDeCartes pioche;
    /**
     * Liste de cartes dans la défausse
     */
    private ListeDeCartes defausse;
    /**
     * Liste des cartes en jeu (cartes jouées par le joueur pendant le tour)
     */
    private ListeDeCartes cartesEnJeu;
    /**
     * Liste des cartes reçues pendant le tour
     */
    private ListeDeCartes cartesRecues;
    /**
     * Couleur du joueur (utilisé par l'interface graphique)
     */
    private CouleurJoueur couleur;
    private int nbPointsCourants;
    private ArrayList<Tuile> coordonnees;

    public Joueur(Jeu jeu, String nom, CouleurJoueur couleur) {
        this.jeu = jeu;
        this.nom = nom;
        this.couleur = couleur;
        argent = 0;
        pointsRails = 0;
        nbJetonsRails = 20;
        main = new ListeDeCartes();
        defausse = new ListeDeCartes();
        pioche = new ListeDeCartes();
        cartesEnJeu = new ListeDeCartes();
        cartesRecues = new ListeDeCartes();

        // créer 7 Train omnibus (non disponibles dans la réserve)
        pioche.addAll(FabriqueListeDeCartes.creerListeDeCartes("Train omnibus", 7));
        // prendre 2 Pose de rails de la réserve
        for (int i = 0; i < 2; i++) {
            pioche.add(jeu.prendreDansLaReserve("Pose de rails"));
        }
        // prendre 1 Gare de la réserve
        pioche.add(jeu.prendreDansLaReserve("Gare"));

        // mélanger la pioche
        pioche.melanger();
        // Piocher 5 cartes en main
        // Remarque : on peut aussi appeler piocherEnMain(5) si la méthode est écrite
        for (int i = 0; i < 5; i++) {
            main.add(pioche.remove(0));
        }

        coordonnees = new ArrayList<>();

    }

    public String getNom() {
        return nom;
    }

    public CouleurJoueur getCouleur() {
        return couleur;
    }

    public ListeDeCartes getMain(){
        return main;
    }

    public int getNbJetonsRails(){
        return nbJetonsRails;
    }

    public void ajouterNbJetonsRails(int n){
        nbJetonsRails += n;
    }

    public int getArgent() {
        return argent;
    }

    public int getPointsRails(){
        return pointsRails;
    }

    public void ajouterPointsRails(int n){
        pointsRails += n;
    }

    public ListeDeCartes getCartesEnJeu() {
        return cartesEnJeu;
    }

    public ListeDeCartes getCartesRecues() {
        return cartesRecues;
    }
    public Jeu getJeu(){ return jeu;}

    public void setArgent(int argent) {
        this.argent = argent;
    }

    public void ajouterArgent(int n){
        argent += n;
    }

    public void ajouterAuDessusDeLaPioche(Carte carte){
        pioche.add(0, carte);
    }

    public void setMain(ListeDeCartes main) {
        this.main = main;
    }

    public void ajouterMain(Carte carte) {
        main.add(carte);
    }

    public void piocherEtAjouterMain(int n){
        List<Carte> main = piocher(n);
        if (!main.isEmpty()){
            this.main.addAll(main);
        }
    }

    public void piocherEtAjouterMain(){
        Carte carte = piocher();
        main.add(carte);
    }

    public void defausser(Carte carte){
        defausse.add(carte);
    }

    public void defausser(List<Carte> cartes){
        defausse.addAll(cartes);
    }

    public ListeDeCartes getDefausse(){
        return defausse;
    }

    public void prendreDefausseEtAjouterMain(String nomCarte){
        for (Carte c : defausse){
            if (c.getNom().equals(nomCarte)){
                main.add(c);
                defausse.retirer(c);
                break;
            }
        }
    }

    public void setCartesEnJeu(ListeDeCartes cartesEnJeu) {
        this.cartesEnJeu = cartesEnJeu;
    }

    public void setCartesRecues(ListeDeCartes cartesRecues) {
        this.cartesRecues = cartesRecues;
    }

    public ListeDeCartes getPioche(){
        return pioche;
    }

    /**
     * Renvoie le score total du joueur
     * <p>
     * Le score total est la somme des points obtenus par les effets suivants :
     * <ul>
     * <li>points de rails (villes et lieux éloignés sur lesquels le joueur a posé
     * un rail)
     * <li>points des cartes possédées par le joueur (cartes VICTOIRE jaunes)
     * <li>score courant du joueur (points marqués en jouant des cartes pendant la
     * partie p.ex. Train de tourisme)
     * </ul>
     * 
     * @return le score total du joueur
     */
    public int getScoreTotal() {
        // À FAIRE
        int somme = 0;
        for (Carte c : defausse){
            somme += c.getNbPrestige();
        }
        for (Carte c : pioche){
            somme += c.getNbPrestige();
        }
        for (Carte c : main){
            somme += c.getNbPrestige();
        }
        //somme += pointsRails;
        somme += nbPointsCourants;
        return somme;
    }

    /**
     * Retire et renvoie la première carte de la pioche.
     * <p>
     * Si la pioche est vide, la méthode commence par mélanger toute la défausse
     * dans la pioche.
     *
     * @return la carte piochée ou {@code null} si aucune carte disponible
     */
    public Carte piocher() {
        if (!(pioche.isEmpty())) {
            return pioche.remove(0);
        } else {
            defausse.melanger();
            pioche.addAll(defausse);
            defausse.clear();
            if (!(pioche.isEmpty())){
                return pioche.remove(0);
            }
            else {
                return null;
            }
        }
    }

    /**
     * Retire et renvoie les {@code n} premières cartes de la pioche.
     * <p>
     * Si à un moment il faut encore piocher des cartes et que la pioche est vide,
     * la défausse est mélangée et toutes ses cartes sont déplacées dans la pioche.
     * S'il n'y a plus de cartes à piocher la méthode s'interromp et les cartes qui
     * ont pu être piochées sont renvoyées.
     * 
     * @param n nombre de cartes à piocher
     * @return une liste des cartes piochées (la liste peut contenir moins de n
     *         éléments si pas assez de cartes disponibles dans la pioche et la
     *         défausse)
     */
    public List<Carte> piocher(int n) {
        List<Carte> res = new ListeDeCartes();
        for (int i = 0; i < n ; i++) {
            res.add(piocher());
        }
        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    public void piocherFeraille(int n){
        for (int i = 0; i < n; i++){
            Carte carte = jeu.prendreDansLaReserve("Ferraille");
            cartesRecues.add(carte);
        }
    }

    public void removeCarte(Carte carte){
        main.remove(carte);
    }

    public void ajouterCoordonnees(Tuile t){
        coordonnees.add(t);
    }

    /**
     * Joue un tour complet du joueur
     * <p>
     * Le tour du joueur se déroule en plusieurs étapes :
     * <ol>
     * <li>Initialisation
     * <p>
     * Dans ce jeu il n'y a rien de particulier à faire en début de tour à part un
     * éventuel affichage dans le log.
     * 
     * <li>Boucle principale
     * <p>
     * C'est le cœur de la fonction. Tant que le tour du joueur n'est pas terminé,
     * il faut préparer la liste de tous les choix valides que le joueur peut faire
     * (jouer des cartes, poser des rails, acheter des cartes, etc.), puis demander
     * au joueur de choisir une action (en appelant la méthode {@code choisir}).
     * <p>
     * Ensuite, en fonction du choix du joueur il faut exécuter l'action demandée et
     * recommencer la boucle si le tour n'est pas terminé.
     * <p>
     * Le tour se termine lorsque le joueur décide de passer (il choisit {@code ""})
     * ou lorsqu'il exécute une action qui termine automatiquement le tour (par
     * exemple s'il choisit de recycler toutes ses cartes Ferraille en début de
     * tour)
     * 
     * <li>Finalisation
     * <p>
     * Actions à exécuter à la fin du tour : réinitialiser les attributs
     * du joueur qui sont spécifiques au tour (argent, rails, etc.), défausser
     * toutes les
     * cartes, piocher 5 nouvelles cartes en main, etc.
     * </ol>
     */
    public void jouerTour() {
        // Initialisation
        jeu.log("<div class=\"tour\">Tour de " + toLog() + "</div>");
        // À FAIRE: compléter l'initialisation du tour si nécessaire (mais possiblement
        // rien de spécial à faire)

        boolean finTour = false;
        List<String> choixChoisis = new ArrayList<>();

        boolean ferraille = false;
        boolean rails = false;
        boolean enleveSurcout = false;
        boolean enleveSurcoutMontagne = false;
        boolean enleveSurcoutVille = false;
        boolean enleveSurcoutRiviere = false;
        boolean enleveSurcoutJoueurs = false;
        int k = 0;

        // Boucle principale
        while (!finTour) {
            List<String> choixPossibles = new ArrayList<>();

            boolean pasDAutreCartes = false;

            // À FAIRE: préparer la liste des choix possibles
            for (Carte c: main) {
                // ajoute les noms de toutes les cartes en main
                choixPossibles.add(c.getNom());
            }

            for (String nomCarte: jeu.getReserve().keySet()) {
                // ajoute les noms des cartes dans la réserve préfixés de "ACHAT:"
                choixPossibles.add("ACHAT:" + nomCarte);
            }

            choixPossibles.add("oui");
            choixPossibles.add("non");
            choixPossibles.add("");
            int size = cartesRecues.size();

            // Choix de l'action à réaliser
            String choix = choisir(String.format("Tour de %s", this.nom), choixPossibles, null, true);
            choixChoisis.add(choix);
            // À FAIRE: exécuter l'action demandée par le joueur
            if (choix.startsWith("ACHAT:")) {
                // prendre une carte dans la réserve
                String nomCarte = choix.split(":")[1];
                Carte carte = jeu.voirLaReserve(nomCarte);
                if (carte != null && argent >= carte.getCout()) {
                    jeu.prendreDansLaReserve(nomCarte);
                    log("Reçoit " + carte); // affichage dans le log
                    argent -= carte.getCout();
                    cartesRecues.add(carte);
                    if (carte.isFeraille() && !ferraille){
                        casIsFeraille();
                    }
                }
                else {
                    assert carte != null;
                    int i = (carte.getCout() - argent);
                    String cost = String.valueOf(i);
                    log("Pas assez d'argent, il manque " + cost);
                    choixChoisis.remove(choixChoisis.size()-1);
                }
            } else if (choix.isEmpty()) {
                // terminer le tour
                finTour = true;

            } else if (choix.equals("oui") || choix.equals("non")){
                casIsOuiNon(choix, choixChoisis);
            }
            else if ((choix.equals("Ferraille") || choix.equals("")) && k == 0) {
                ArrayList<Carte> cartes = new ArrayList<>();
                for (Carte c : main){
                    if (c.getNom().equals("Ferraille")){
                        cartes.add(c);
                    }
                }
                jeu.ajouterReserve(cartes);
                main.retirer(cartes);
            }
            else {

                Carte carte = main.retirer(choix);
                if (carte != null && Objects.equals(carte.getType(), "Rail")){
                    if (choix.equals("Voie souterraine")){
                        enleveSurcout = true;
                    }
                    else if (choix.equals("Tunnel")){
                        enleveSurcoutMontagne = true;
                    }
                    else if (choix.equals("Viaduc")){
                        enleveSurcoutVille = true;
                    }
                    else if (choix.equals("Pont en acier")){
                        enleveSurcoutRiviere = true;
                    }
                    else if (choix.equals("Coopération")){
                        enleveSurcoutJoueurs = true;
                    }
                    log("Joue " + carte); // affichage dans le log
                    cartesEnJeu.add(carte); // mettre la carte en jeu
                    if (rails) argent +=2;
                    carte.jouer(this, enleveSurcout, enleveSurcoutMontagne, enleveSurcoutVille, enleveSurcoutRiviere, enleveSurcoutJoueurs);  // exécuter l'action de la carte

                }
                else if (carte != null){
                    log("Joue " + carte); // affichage dans le log
                    cartesEnJeu.add(carte); // mettre la carte en jeu
                    carte.jouer(this);  // exécuter l'action de la carte
                }

                if (choix.equals("Dépotoir")){
                    ferraille = true;
                }
                else if (choix.equals("Ferronnerie")){
                    rails = true;
                }


            }
            if (ferraille && !choix.equals("Dépotoir") && cartesRecues.size() > size){
                int diff = cartesRecues.size() - size;
                for (int i = 1; i <= diff; i++){
                    if (cartesRecues.get(cartesRecues.size() - i).getNom().equals("Ferraille")){
                        jeu.ajouterReserve(cartesRecues.get(cartesRecues.size() - i));
                        cartesRecues.remove(cartesRecues.get(cartesRecues.size() - i));
                    }
                }
            }


            if (rails && !choix.equals("Ferronnerie") && cartesEnJeu.getCarte(choix)!=null && cartesEnJeu.getCarte(choix).getType().equals("Rail")){
                argent += 2;
            }
            k++;
        }
        // Finalisation
        // À FAIRE: compléter la finalisation du tour
        // défausser toutes les cartes
        defausse.addAll(main);
        main.clear();
        defausse.addAll(cartesRecues);
        cartesRecues.clear();
        defausse.addAll(cartesEnJeu);
        cartesEnJeu.clear();
        argent = 0;
        pointsRails = 0;
        main.addAll(piocher(5)); // piocher 5 cartes en main
    }

    public void casIsFeraille(){
        Carte carteFeraille = jeu.prendreDansLaReserve("Ferraille");
        cartesRecues.add(carteFeraille);
        log("Reçoit " + carteFeraille);
    }

    public void casIsOuiNon(String choix, List<String> choixChoisis){
        if (choix.equals("oui")){
            if (choixChoisis.size() > 1){
                if (choixChoisis.get(choixChoisis.size()-2).startsWith("ACHAT:") && verifierChoixChoisis(choixChoisis, "Train matinal")){
                    String[] mots = choixChoisis.get(choixChoisis.size()-2).split(":");
                    casIsTrainMatinal(mots[1]);
                }
            }
        }
    }

    public boolean verifierChoixChoisis(List<String> choixChoisis, String nomCarte){
        int i = 0;
        boolean check = false;
        while (i < choixChoisis.size() && !check){
            if (choixChoisis.get(i).equals(nomCarte)){
                check = true;
            }
            i++;
        }
        return check;
    }

    public void casIsTrainMatinal(String nomCarte){
        Carte carte = cartesRecues.retirer(nomCarte);
        pioche.add(0, carte);
    }

    /**
     * Attend une entrée de la part du joueur (au clavier ou sur la websocket) et
     * renvoie le choix du joueur.
     * <p>
     * Cette méthode lit les entrées du jeu ({@code Jeu.lireligne()}) jusqu'à ce
     * qu'un choix valide (un élément de {@code choix} ou la valeur d'un élément de
     * {@code boutons} ou éventuellement la chaîne vide si l'utilisateur est
     * autorisé à passer) soit reçu.
     * Lorsqu'un choix valide est obtenu, il est renvoyé par la fonction.
     * <p>
     * Exemple d'utilisation pour demander à un joueur de répondre à une question
     * par "oui" ou "non" :
     * <p>
     * 
     * <pre>{@code
     * List<String> choix = Arrays.asList("oui", "non");
     * String input = choisir("Voulez-vous faire ceci ?", choix, null, false);
     * }</pre>
     * <p>
     * Si par contre on voulait proposer les réponses à l'aide de boutons, on
     * pourrait utiliser :
     * 
     * <pre>{@code
     * List<String> boutons = Arrays.asList(new Bouton("Oui !", "oui"), new Bouton("Non !", "non"));
     * String input = choisir("Voulez-vous faire ceci ?", null, boutons, false);
     * }</pre>
     * 
     * (ici le premier bouton a le label "Oui !" et envoie la String "oui" s'il est
     * cliqué, le second a le label "Non !" et envoie la String "non" lorsqu'il est
     * cliqué)
     *
     * <p>
     * <b>Remarque :</b> Normalement, si le paramètre {@code peutPasser} est
     * {@code false} le choix
     * {@code ""} n'est pas valide. Cependant s'il n'y a aucun choix proposé (les
     * listes {@code choix} et {@code boutons} sont vides ou {@code null}), le choix
     * {@code ""} est accepté pour éviter un blocage.
     * 
     * @param instruction message à afficher à l'écran pour indiquer au joueur la
     *                    nature du choix qui est attendu
     * @param choix       une collection de chaînes de caractères correspondant aux
     *                    choix valides attendus du joueur (ou {@code null})
     * @param boutons     une liste d'objets de type {@code Bouton} définis par deux
     *                    chaînes de caractères (label, valeur) correspondant aux
     *                    choix valides attendus du joueur qui doivent être
     *                    représentés par des boutons sur l'interface graphique (le
     *                    label est affiché sur le bouton, la valeur est ce qui est
     *                    envoyé au jeu quand le bouton est cliqué) ou {@code null}
     * @param peutPasser  booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la
     *                    chaîne de caractères vide ({@code ""}) qui signifie qu'il
     *                    désire passer.
     * @return le choix de l'utilisateur (un élement de {@code choix}, ou la valeur
     *         d'un élément de {@code boutons} ou la chaîne vide)
     */
    public String choisir(
            String instruction,
            Collection<String> choix,
            List<Bouton> boutons,
            boolean peutPasser) {
        if (choix == null)
            choix = new ArrayList<>();
        if (boutons == null)
            boutons = new ArrayList<>();

        HashSet<String> choixDistincts = new HashSet<>(choix);
        choixDistincts.addAll(boutons.stream().map(Bouton::valeur).toList());
        if (peutPasser || choixDistincts.isEmpty()) {
            // si le joueur a le droit de passer ou s'il n'existe aucun choix valide, on
            // ajoute "" à la liste des choix possibles
            choixDistincts.add("");
        }

        String entree;
        // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
        while (true) {
            jeu.prompt(instruction, boutons, peutPasser);
            entree = jeu.lireLigne();
            // si une réponse valide est obtenue, elle est renvoyée
            if (choixDistincts.contains(entree)) {
                return entree;
            }
        }
    }

    public Tuile choisirPosition(Collection<String> choix){
        String a = choisir(this.nom + " choisit une tuile de départ en cliquant dessus", choix, null, false);
        String [] words = a.split(":");
        if (isNumeric(words[1]) > -1){
            coordonnees.add(jeu.getTuile(isNumeric(words[1])));
        }
        return jeu.getTuile(isNumeric(words[1]));
    }

    /**
     * Ajoute un message dans le log du jeu
     * 
     * @param message message à ajouter dans le log
     */
    public void log(String message) {
        jeu.log(message);
    }

    @Override
    public String toString() {
        // Vous pouvez modifier cette fonction comme bon vous semble pour afficher
        // d'autres informations si nécessaire
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("=== %s (%d pts) ===", nom, getScoreTotal()));
        joiner.add(String.format("  Argent: %d  Rails: %d", argent, pointsRails));
        joiner.add("  Cartes en jeu: " + cartesEnJeu);
        joiner.add("  Cartes reçues: " + cartesRecues);
        joiner.add("  Cartes en main: " + main);
        return joiner.toString();
    }

    /**
     * @return une représentation du joueur pour l'affichage dans le log du jeu
     */
    public String toLog() {
        return String.format("<span class=\"joueur %s\">%s</span>", couleur.toString(), nom);
    }

    /**
     * @return une représentation du joueur sous la forme d'un dictionnaire de
     *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     *         l'interface graphique)
     */
    Map<String, Object> dataMap() {
        return Map.ofEntries(
                Map.entry("nom", nom),
                Map.entry("couleur", couleur),
                Map.entry("scoreTotal", getScoreTotal()),
                Map.entry("argent", argent),
                Map.entry("rails", pointsRails),
                Map.entry("nbJetonsRails", nbJetonsRails),
                Map.entry("main", main.dataMap()),
                Map.entry("defausse", defausse.dataMap()),
                Map.entry("cartesEnJeu", cartesEnJeu.dataMap()),
                Map.entry("cartesRecues", cartesRecues.dataMap()),
                Map.entry("pioche", pioche.dataMap()),
                Map.entry("actif", jeu.getJoueurCourant() == this));
    }

    public ArrayList<Tuile> getCoordonnees(){
        return coordonnees;
    }

    public void ajouterPointScoreTotal(int n){
        this.nbPointsCourants += n;
    }

    public void ajouterCartesRecues(Carte carte){
        this.cartesRecues.add(carte);
    }

    public List<Carte> prendreCarteDeMain(String nomCarte){
        List<Carte> cartes = new ArrayList<>();
        List<Carte> main = new ArrayList<>(this.main);
        for (Carte c : main){
            if (c.getNom().equals(nomCarte)){
                cartes.add(c);
                this.main.remove(c);
            }
        }
        return cartes;
    }

    public void devoilerCarte(Carte carte) {
        log("Le joueur " + nom + " dévoile la carte : " + carte.toString());
    }

    public void devoilerCartes(ListeDeCartes cartes) {
        log("Le joueur " + nom + " dévoile les cartes : " + cartes.toString());
    }

    public void message(String texte) {
        log(texte);
    }

    public String lireLigne() {
        return jeu.lireLigne();
    }

    public Carte retirerDeLaReserve(String nomCarte) {
        return jeu.prendreDansLaReserve(nomCarte);
    }


    /*
    public void modifierNbJetonsRails(int i){
        nbJetonsRails = i;
    }*/
}
