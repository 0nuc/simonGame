package fr.esgi.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    @Test
    void testGetPrenom() {
        Joueur joueur = new Joueur("prenom");
        assertEquals("prenom", joueur.getPrenom(), "Le prénom du joueur doit être correctement initialisé.");
    }

    @Test
    void testSetPrenom() {
        Joueur joueur = new Joueur("prenom");
        joueur.setPrenom("nouveauPrenom");
        assertEquals("nouveauPrenom", joueur.getPrenom(), "Le prénom du joueur doit être mis à jour.");
    }

    @Test
    void testGetScore() {
        Joueur joueur = new Joueur("prenom");
        assertEquals(0, joueur.getScore(), "Le score initial du joueur doit être 0.");
    }

    @Test
    void testIncrementScore() {
        Joueur joueur = new Joueur("prenom");
        joueur.incrementScore();
        assertEquals(1, joueur.getScore(), "Le score doit être incrémenté de 1.");
        joueur.incrementScore();
        assertEquals(2, joueur.getScore(), "Le score doit être incrémenté de 1 à chaque appel.");
    }
}