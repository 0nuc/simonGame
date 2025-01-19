package fr.esgi.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    @Test
    void testConstructeur() {
        // Création d'un joueur avec un prénom
        Joueur joueur = new Joueur("Alice");

        // Vérification que le prénom est correctement initialisé
        assertEquals("Alice", joueur.getPrenom());

        // Vérification que le score est initialisé à 0
        assertEquals(0, joueur.getScore());
    }

    @Test
    void testGetAndSetPrenom() {
        // Création d'un joueur
        Joueur joueur = new Joueur("Alice");

        // Changer le prénom
        joueur.setPrenom("Bob");

        // Vérification que le prénom a été mis à jour
        assertEquals("Bob", joueur.getPrenom());
    }

    @Test
    void testIncrementScore() {
        // Création d'un joueur
        Joueur joueur = new Joueur("Alice");

        // Incrémenter le score
        joueur.incrementScore();
        joueur.incrementScore();

        // Vérification que le score est correctement incrémenté
        assertEquals(2, joueur.getScore());
    }
}