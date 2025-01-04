package fr.esgi.controller;

import fr.esgi.business.Joueur;
import fr.esgi.utils.JavaFXThreadingRule;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerNameControllerTest {

    @InjectMocks
    private PlayerNameController playerNameController;

    @BeforeAll
    static void initToolkit() {
        JavaFXThreadingRule.initToolkit(); // Initialize JavaFX once
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        playerNameController.playerNameFields = new VBox();
    }

    @Test
    void testSetNumberOfPlayers() {
        playerNameController.setNumberOfPlayers(3);
        assertEquals(3, playerNameController.playerNameFields.getChildren().size());
        assertTrue(playerNameController.playerNameFields.getChildren().get(0) instanceof TextField);
        assertEquals("Prénom du joueur 1",
                ((TextField) playerNameController.playerNameFields.getChildren().get(0)).getPromptText());
    }

    @Test
    void testSavePlayerNames() {
        playerNameController.setNumberOfPlayers(2);
        ((TextField) playerNameController.playerNameFields.getChildren().get(0)).setText("Alice");
        ((TextField) playerNameController.playerNameFields.getChildren().get(1)).setText("Bob");

        playerNameController.savePlayerNames();

        List<Joueur> joueurs = playerNameController.getJoueurs();
        assertEquals(2, joueurs.size());
        assertEquals("Alice", joueurs.get(0).getPrenom());
        assertEquals("Bob", joueurs.get(1).getPrenom());
    }
}
