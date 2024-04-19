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
        //assertTrue(containsReferences(cartesRecues, app));
        assertEquals(1, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
        assertFalse(containsReference(reserve.get("Appartement"), app));
    }

}
