package fr.esgi.business;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JoueurTest {

    @Test
    @DisplayName("Test du constructeur par défaut")
    void testerConstructeurParDefaut() {
        // Arrange & Act
        Joueur joueur = new Joueur();

        // Assert
        assertNotNull(joueur, "L'objet Joueur ne doit pas être null.");
        assertNull(joueur.getPrenom(), "Le prénom doit être null par défaut.");
        assertEquals(0, joueur.getScore(), "Le score initial doit être 0.");
    }

    @Test
    @DisplayName("Test du constructeur avec prénom")
    void testerConstructeurAvecPrenom() {
        // Arrange
        String prenom = "Alice";

        // Act
        Joueur joueur = new Joueur(prenom);

        // Assert
        assertNotNull(joueur, "L'objet Joueur ne doit pas être null.");
        assertEquals(prenom, joueur.getPrenom(), "Le prénom doit correspondre à celui fourni.");
        assertEquals(0, joueur.getScore(), "Le score initial doit être 0.");
    }

    @Test
    @DisplayName("Test du constructeur avec prénom et score")
    void testerConstructeurAvecPrenomEtScore() {
        // Arrange
        String prenom = "Bob";
        int score = 10;

        // Act
        Joueur joueur = new Joueur(prenom, score);

        // Assert
        assertNotNull(joueur, "L'objet Joueur ne doit pas être null.");
        assertEquals(prenom, joueur.getPrenom(), "Le prénom doit correspondre à celui fourni.");
        assertEquals(score, joueur.getScore(), "Le score doit correspondre à celui fourni.");
    }

    @Test
    @DisplayName("Test du setter pour le prénom")
    void testerSetterPrenom() {
        // Arrange
        Joueur joueur = new Joueur("Alice");

        // Act
        joueur.setPrenom("Charlie");

        // Assert
        assertEquals("Charlie", joueur.getPrenom(), "Le prénom doit être mis à jour à 'Charlie'.");
    }

    @Test
    @DisplayName("Test de l'incrémentation du score")
    void testerIncrementScore() {
        // Arrange
        Joueur joueur = new Joueur("Alice", 5);

        // Act
        joueur.incrementScore();

        // Assert
        assertEquals(6, joueur.getScore(), "Le score doit être incrémenté de 1.");
    }

    @Test
    @DisplayName("Test de la méthode equals")
    void testerEquals() {
        // Arrange
        Joueur joueur1 = new Joueur("Alice", 5);
        Joueur joueur2 = new Joueur("Alice", 5);
        Joueur joueur3 = new Joueur("Bob", 10);

        // Act & Assert
        assertEquals(joueur1, joueur2, "Deux joueurs avec les mêmes valeurs doivent être égaux.");
        assertNotEquals(joueur1, joueur3, "Deux joueurs avec des valeurs différentes ne doivent pas être égaux.");
    }

    @Test
    @DisplayName("Test de la méthode hashCode")
    void testerHashCode() {
        // Arrange
        Joueur joueur1 = new Joueur("Alice", 5);
        Joueur joueur2 = new Joueur("Alice", 5);

        // Act & Assert
        assertEquals(joueur1.hashCode(), joueur2.hashCode(), "Deux joueurs égaux doivent avoir le même hashCode.");
    }

    @Test
    @DisplayName("Test de la méthode toString")
    void testerToString() {
        // Arrange
        Joueur joueur = new Joueur("Alice", 5);

        // Act
        String representation = joueur.toString();

        // Assert
        assertNotNull(representation, "La méthode toString ne doit pas retourner null.");
        assertTrue(representation.contains("Alice"), "La représentation doit contenir le prénom.");
        assertTrue(representation.contains("5"), "La représentation doit contenir le score.");
    }
}