package fr.esgi.business;

import fr.esgi.service.JeuService;
import fr.esgi.service.SonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JeuTest {

    @Mock
    private JeuService jeuService;

    @Mock
    private SonService sonService;

    @Mock
    private List<Joueur> mockJoueurs;

    @InjectMocks
    private Jeu jeu;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jeu = Jeu.builder()
                .joueurs(mockJoueurs)
                .jeuService(jeuService)
                .sonService(sonService)
                .build();
    }

    @Test
    @DisplayName("Test du constructeur et des getters")
    void testerConstructeurEtGetters() {
        // Arrange
        List<Joueur> joueurs = Arrays.asList(new Joueur("Alice"), new Joueur("Bob"));

        // Act
        Jeu jeu = new Jeu(joueurs);

        // Assert
        assertNotNull(jeu, "L'objet Jeu ne doit pas être null.");
        assertEquals(joueurs, jeu.getJoueurs(), "La liste des joueurs doit correspondre à celle fournie.");
    }

    @Test
    @DisplayName("Test des setters")
    void testerSetters() {
        // Arrange
        List<Joueur> nouveauxJoueurs = Arrays.asList(new Joueur("Charlie"), new Joueur("Dave"));

        // Act
        jeu.setJoueurs(nouveauxJoueurs);

        // Assert
        assertEquals(nouveauxJoueurs, jeu.getJoueurs(), "Les joueurs définis doivent être mis à jour correctement.");
    }

    @Test
    @DisplayName("Test du démarrage du jeu")
    void testerDemarrageJeu() {
        // Act
        jeu.getJeuService().startGame();
        jeu.getSonService().playSound("startSound");

        // Assert
        verify(jeuService, times(1)).startGame();
        verify(sonService, times(1)).playSound("startSound");
    }

    @Test
    @DisplayName("Test de la fin du jeu")
    void testerFinJeu() {
        // Act
        jeu.getJeuService().endGame();
        jeu.getSonService().stopSound();

        // Assert
        verify(jeuService, times(1)).endGame();
        verify(sonService, times(1)).stopSound();
    }

    @Test
    @DisplayName("Test de la méthode equals")
    void testerEquals() {
        // Arrange
        Jeu jeu1 = Jeu.builder()
                .joueurs(mockJoueurs)
                .jeuService(jeuService)
                .sonService(sonService)
                .build();
        Jeu jeu2 = Jeu.builder()
                .joueurs(mockJoueurs)
                .jeuService(jeuService)
                .sonService(sonService)
                .build();

        // Act & Assert
        assertEquals(jeu1, jeu2, "Deux objets Jeu avec les mêmes valeurs doivent être égaux.");
    }

    @Test
    @DisplayName("Test de la méthode toString")
    void testerToString() {
        // Act
        String representation = jeu.toString();

        // Assert
        assertNotNull(representation, "La méthode toString ne doit pas retourner null.");
        assertTrue(representation.contains("joueurs"), "La représentation doit inclure le champ joueurs.");
        assertTrue(representation.contains("jeuService"), "La représentation doit inclure le champ jeuService.");
        assertTrue(representation.contains("sonService"), "La représentation doit inclure le champ sonService.");
    }
}