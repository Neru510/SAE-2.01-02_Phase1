package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.Tuile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static fr.umontpellier.iut.trains.Jeu.isNumeric;

public class PoseDeRails extends Rail {
    public PoseDeRails() {
        super("Pose de rails", 3);
    }
}
