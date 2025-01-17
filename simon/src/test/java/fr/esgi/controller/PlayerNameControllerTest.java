package fr.esgi.controller;

import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerNameControllerTest {

    private PlayerNameController controller;

    @BeforeAll
    public static void initToolkit() {
        try {
            Platform.startup(() -> {
                // JavaFX Toolkit initialisé
            });
        } catch (IllegalStateException e) {
            // Toolkit déjà initialisé
            System.out.println("JavaFX Toolkit already initialized.");
        }
    }

    @Test
    void testLoadPlayerNameFields() {
        controller = new PlayerNameController();

        VBox playerNameFields = new VBox();
        controller.playerNameFields = playerNameFields;

        controller.setNumberOfPlayers(3);

        assertEquals(3, playerNameFields.getChildren().size());
    }

    @Test
    void testGetPlayerNames() {
        controller = new PlayerNameController();

        VBox playerNameFields = new VBox();
        TextField player1 = new TextField("Alice");
        TextField player2 = new TextField("Bob");
        playerNameFields.getChildren().addAll(player1, player2);

        controller.playerNameFields = playerNameFields;

        assertEquals(2, controller.getPlayerNames().size());
        assertEquals("Alice", controller.getPlayerNames().get(0));
        assertEquals("Bob", controller.getPlayerNames().get(1));
    }
}