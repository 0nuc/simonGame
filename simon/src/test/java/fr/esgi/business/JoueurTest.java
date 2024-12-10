package fr.esgi.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JoueurTest {

    @Test
    @DisplayName("Test du constructeur et des getters")
    void testerConstructeurEtGetters() {
        // Arrange
        String prenom = "Alice";

        // Act
        Joueur joueur = new Joueur(prenom);

        // Assert
        assertNotNull(joueur, "L'objet joueur ne doit pas être null.");
        assertEquals(prenom, joueur.getPrenom(), "Le prénom du joueur doit être 'Alice'.");
        assertEquals(0, joueur.getScore(), "Le score initial du joueur doit être 0.");
    }

    @Test
    @DisplayName("Test du setter pour le prénom")
    void testerSetterPrenom() {
        // Arrange
        Joueur joueur = new Joueur("Alice");

        // Act
        joueur.setPrenom("Bob");

        // Assert
        assertEquals("Bob", joueur.getPrenom(), "Le prénom du joueur doit être mis à jour à 'Bob'.");
    }

    @Test
    @DisplayName("Test de l'incrémentation du score")
    void testerIncrementScore() {
        // Arrange
        Joueur joueur = new Joueur("Alice");

        // Act
        joueur.incrementScore();
        int scoreApresIncrementation = joueur.getScore();

        // Assert
        assertEquals(1, scoreApresIncrementation, "Le score doit être incrémenté à 1.");
    }
}