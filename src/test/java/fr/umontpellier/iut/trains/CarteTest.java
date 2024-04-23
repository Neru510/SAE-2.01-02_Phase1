package fr.umontpellier.iut.trains;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.umontpellier.iut.trains.cartes.*;

import static org.junit.jupiter.api.Assertions.*;

public class CarteTest extends BaseTestClass {

    @Test
    void test_depotoir() {
        setupJeu("Dépotoir");
        initialisation();

        Carte c = new Depotoir();
        Carte poseDeRail = new PoseDeRails();
        Carte fondPioche = new Ferraille();

        addAll(main, c, poseDeRail);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Dépotoir", "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, poseDeRail));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_depotoir_achat() {
        setupJeu("Dépotoir");
        initialisation();

        Carte c = new Depotoir();
        Carte poseDeRail = new PoseDeRails();
        Carte trd1 = new TrainDirect();
        Carte fondPioche = new Ferraille();
        Carte app = reserve.get("Appartement").get(0);

        addAll(main, c, poseDeRail, trd1);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Dépotoir", "Pose de rails", "Train direct", "ACHAT:Appartement");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, poseDeRail, trd1));
        assertTrue(containsReferences(cartesRecues, app));
        assertEquals(1, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
        assertFalse(containsReference(reserve.get("Appartement"), app));
    }

    @Test
    void test_ferronnerie() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte c = new Ferronnerie();
        Carte rails = new PoseDeRails();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, rails);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Ferronnerie", "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, rails));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(3, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_train_postal() {
        setupJeu("Train postal");
        initialisation();

        Carte c = new TrainPostal();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte imm = new Immeuble();
        Carte imm2 = new Immeuble();

        addAll(main, c, imm, imm2, gare);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train postal", "Immeuble", "Immeuble", "");

        assertTrue(containsReferences(main, gare));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, imm, imm2));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(3, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_pose_de_rails_surcout_plaine(){
        setupJeu("Pose de rails");
        initialisation();

        Carte c = new PoseDeRails();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Pose de rails", "TUILE:20");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_pose_de_rails_surcout_riviere(){

    }

    @Test
    void test_pose_de_rails_surcout_montagne(){

    }

    @Test
    void test_pose_de_rails_surcout_ville(){

    }

    @Test
    void test_pose_de_rails_surcout_lieu_eloigné(){

    }

    @Test
    void test_pose_de_rails_surcout_autres_jetons_joueurs(){

    }

    @Test
    void test_pose_de_rails_surcout_mer(){

    }

}
