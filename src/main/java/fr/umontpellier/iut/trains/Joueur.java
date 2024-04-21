package fr.umontpellier.iut.trains;

import java.util.*;

import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.FabriqueListeDeCartes;
import fr.umontpellier.iut.trains.cartes.ListeDeCartes;

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
    private int[] coordonnees;

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

        coordonnees = new int[100];

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

    public void setMain(ListeDeCartes main) {
        this.main = main;
    }

    public void piocherEtAjouterMain(int n){
        List<Carte> main = piocher(n);
        this.main.addAll(main);
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

    public void setCartesEnJeu(ListeDeCartes cartesEnJeu) {
        this.cartesEnJeu = cartesEnJeu;
    }

    public void setCartesRecues(ListeDeCartes cartesRecues) {
        this.cartesRecues = cartesRecues;
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
        somme += pointsRails;
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
            Carte carte = jeu.prendreDansLaReserve("Feraille");
            cartesRecues.add(carte);
        }
    }

    public void removeCarte(Carte carte){
        main.remove(carte);
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
        // Boucle principale
        while (!finTour) {
            List<String> choixPossibles = new ArrayList<>();

            boolean pasDAutreCartes = false;

            // À FAIRE: préparer la liste des choix possibles
            if (!choixChoisis.isEmpty() && choixChoisis.get(choixChoisis.size()-1).equals("Parc d'attractions")){
                for (Carte c: cartesEnJeu) {
                    // ajoute les noms de toutes les cartes en main
                    if (c.getType().equals("Train")){
                        choixPossibles.add(c.getNom());
                    }
                }
            }
            else if (!choixChoisis.isEmpty() && choixChoisis.get(choixChoisis.size()-1).equals("Bureau du chef de gare")){
                for (Carte c: main) {
                    // ajoute les noms de toutes les cartes en main
                    if (c.getType().equals("Action")){
                        choixPossibles.add(c.getNom());
                    }
                }
            }
            else if (!choixChoisis.isEmpty() && choixChoisis.get(choixChoisis.size()-1).equals("Échangeur")){
                for (Carte c: cartesEnJeu) {
                    // ajoute les noms de toutes les cartes en main
                    if (c.getType().equals("Train")){
                        choixPossibles.add(c.getNom());
                    }
                }
            }
            if (choixPossibles.isEmpty()){
                for (Carte c: main) {
                    // ajoute les noms de toutes les cartes en main
                    choixPossibles.add(c.getNom());
                }
            }

            for (String nomCarte: jeu.getReserve().keySet()) {
                // ajoute les noms des cartes dans la réserve préfixés de "ACHAT:"
                choixPossibles.add("ACHAT:" + nomCarte);
            }

            choixPossibles.add("oui");
            choixPossibles.add("non");
            choixPossibles.add("piocher");
            choixPossibles.add("argent");
            choixPossibles.add("ferraille");

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
                    int i = (argent - carte.getCout());
                    String cost = String.valueOf(i);
                    log("Pas assez d'argent, il manque " + cost);
                }
            } else if (choix.isEmpty()) {
                // terminer le tour
                finTour = true;

            } else if (choix.equals("oui") || choix.equals("non")){
                casIsOuiNon(choix, choixChoisis);
            }
            else if (choixChoisis.size() > 1 && choixChoisis.get(choixChoisis.size()-2).equals("Personnel de gare")){
                casIsPersonneDeGare(choixChoisis);
            }
            else if (choixChoisis.size() > 1 && choixChoisis.get(choixChoisis.size()-2).equals("Bureau du chef de gare")){
                casIsBureauDuChefDeGare(choix);
            }
            else if (choixChoisis.size() > 1 && choixChoisis.get(choixChoisis.size()-2).equals("Parc d'attractions")){
                casIsParcDAttraction(choix);
            }
            else if (choixChoisis.size() > 1 && choixChoisis.get(choixChoisis.size()-2).equals("Échangeur")){
                casIsEchangeur(choix);
            }
            else {
                boolean check = false;
                if (choixChoisis.size() > 1){
                    check = casIsPeutEtreDepot(choix, choixChoisis);
                }
                if (!check){
                    Carte carte = main.retirer(choix);
                    if (carte != null){
                        log("Joue " + carte); // affichage dans le log
                        cartesEnJeu.add(carte); // mettre la carte en jeu
                        carte.jouer(this);  // exécuter l'action de la carte
                    }
                }

                if (choix.equals("Dépotoir")){
                    ferraille = true;
                }

            }
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

        main.addAll(piocher(5)); // piocher 5 cartes en main
    }

    public void casIsEchangeur(String choix){
        Carte c = cartesEnJeu.getCarte(choix);
        if (c != null && c.getType().equals("Train")){
            c = cartesEnJeu.retirer(choix);
            pioche.add(0,c);
        }
    }
    public void casIsParcDAttraction(String choix){
        Carte carte = cartesEnJeu.getCarte(choix);
        if (carte != null && carte.getType().equals("Train")){
            argent += carte.getvaleur();
        }
    }

    public void casIsBureauDuChefDeGare(String choix){
        Carte carte = main.getCarte(choix);
        if (carte != null && carte.getType().equals("Action")){
            carte.jouer(this);
        }
        else {
            Carte c = main.retirer(choix);
            if (c != null){
                log("Joue " + c); // affichage dans le log
                cartesEnJeu.add(c); // mettre la carte en jeu
                c.jouer(this);  // exécuter l'action de la carte
            }
        }
    }
    public void casIsFeraille(){
        Carte carteFeraille = jeu.prendreDansLaReserve("Ferraille");
        cartesRecues.add(carteFeraille);
        log("Reçoit " + carteFeraille);
    }

    public void casIsOuiNon(String choix, List<String> choixChoisis){
        if (choix.equals("oui")){
            if (choixChoisis.size() > 1){
                if (choixChoisis.get(choixChoisis.size() - 2).equals("Horaires estivaux")){
                    casIsHorairesEstivaux(choixChoisis);
                }
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

    public void casIsHorairesEstivaux(List<String> choixChoisis){
        jeu.ecarterCarte(cartesEnJeu.retirer(choixChoisis.get(choixChoisis.size() - 2)));
        ajouterArgent(3);
    }

    public void casIsTrainMatinal(String nomCarte){
        Carte carte = cartesRecues.retirer(nomCarte);
        pioche.add(0, carte);
    }

    public void casIsPersonneDeGare(List<String> choixChoisis){
        switch (choixChoisis.get(choixChoisis.size() - 1)) {
            case "piocher" -> piocherEtAjouterMain();
            case "argent" -> argent++;
            case "ferraille" -> {
                Carte carte = main.retirer("Ferraille");
                if (carte!= null) {
                    main.remove(carte);
                    jeu.ajouterReserve(carte);
                } else {
                    log("Vous n'avez pas de carte ferraille dans votre main");
                }
            }
            default ->
                    log("choisissez entre piocher une carte (taper piocher), gagner une pièce (taper argent), remettre une carte ferraille dans la pioche (taper ferraille)");
        }
    }

    public boolean casIsPeutEtreDepot(String nomcarte, List<String> choixChoisis){
        boolean check = false;
        if (choixChoisis.get(choixChoisis.size()-2).equals("Dépôt")){
            check = casIsDepot(nomcarte);
        }
        else if (choixChoisis.size() > 2){
            if (choixChoisis.get(choixChoisis.size()-3).equals("Dépôt")){
                check = casIsDepot(nomcarte);
            }
        }
        return check;
    }

    public boolean casIsDepot(String nomcarte){
        Carte carte = main.retirer(nomcarte);
        defausse.add(carte);
        main.remove(carte);
        return true;
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

    public void choisirPosition(Collection<String> choix){
        String a = choisir(this.nom + " choisit une tuile de départ en cliquant dessus", choix, null, false);
        String [] words = a.split(":");
        if (Integer.parseInt(words[1]) > -1){
            coordonnees[coordonnees.length-1] = Integer.parseInt(words[1]);
        }
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

    public int[] getCoordonnees(){
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
        List<Carte> main = new ArrayList<>();
        main.addAll(this.main);
        for (Carte c : main){
            if (c.getNom().equals(nomCarte)){
                cartes.add(c);
                this.main.remove(c);
            }
        }
        return cartes;
    }

    public void devoilerCarte(Joueur joueur, Carte carte) {
        log("Le joueur " + joueur.nom + " dévoile la carte : " + carte.toString());
    }

    /*
    public void modifierNbJetonsRails(int i){
        nbJetonsRails = i;
    }*/
}
