package fr.esgi.controller;

import fr.esgi.util.JavaFXInitializer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.util.WaitForAsyncUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

    private GameController controller;

    @BeforeAll
    static void initJavaFX() {
        JavaFXInitializer.initToolkit(); // Initialiser JavaFX avant tous les tests
    }

    @BeforeEach
    void setUp() {
        controller = new GameController();

        // Initialiser les composants JavaFX simulés
        controller.btnRed = new Button();
        controller.btnBlue = new Button();
        controller.btnGreen = new Button();
        controller.btnYellow = new Button();
        controller.btnRestart = new Button();
        controller.btnReplayBest = new Button();
        controller.btnApplyConfig = new Button();
        controller.btnStart = new Button();
        controller.lblScore = new Label();
        controller.colorRed = new ComboBox<>();
        controller.colorBlue = new ComboBox<>();
        controller.colorGreen = new ComboBox<>();
        controller.colorYellow = new ComboBox<>();
        controller.soundChoice = new ComboBox<>();

        // Propriétés initiales
        controller.btnReplayBest.setDisable(true);
        controller.btnReplayBest.setVisible(false);
        controller.btnRestart.setDisable(true);
        controller.btnRestart.setVisible(false);
    }

    @Test
    void testInitialize() {
        controller.initialize();

        // Vérifiez que les combobox ne sont pas vides
        assertFalse(controller.colorRed.getItems().isEmpty());
        assertFalse(controller.soundChoice.getItems().isEmpty());

        // Vérifiez l'état initial des boutons
        assertTrue(controller.btnReplayBest.isDisable());
        assertFalse(controller.btnReplayBest.isVisible());
        assertTrue(controller.btnRestart.isDisable());
        assertFalse(controller.btnRestart.isVisible());
    }

    @Test
    void testStartGame_NoPlayers() {
        controller.players.clear(); // Aucun joueur enregistré

        controller.startGame();

        // Vérifiez que le label affiche le message approprié
        assertEquals("Aucun joueur enregistré !", controller.lblScore.getText());
    }

    @Test
    void testStartGame_WithPlayers() {
        controller.setPlayers(List.of("Player1")); // Ajouter un joueur
        controller.startGame();

        // Vérifiez que le label affiche correctement le score initial
        assertEquals("Joueur: Player1 - Score: 0", controller.lblScore.getText());
        assertEquals(1, controller.sequence.size()); // Une étape a été ajoutée
    }

    @Test
    void testApplyConfigurations() {
        // Simulez les couleurs et sons sélectionnés
        controller.colorRed.getItems().add("#FF0000");
        controller.colorBlue.getItems().add("#0000FF");
        controller.colorGreen.getItems().add("#00FF00");
        controller.colorYellow.getItems().add("#FFFF00");
        controller.soundChoice.getItems().add("guitar.wav");

        controller.colorRed.setValue("#FF0000");
        controller.colorBlue.setValue("#0000FF");
        controller.colorGreen.setValue("#00FF00");
        controller.colorYellow.setValue("#FFFF00");
        controller.soundChoice.setValue("guitar.wav");

        controller.applyConfigurations();

        // Vérifiez que les configurations sont appliquées
        assertEquals("#FF0000", controller.buttonColors.get(controller.btnRed));
        assertEquals("#0000FF", controller.buttonColors.get(controller.btnBlue));
        assertEquals("#00FF00", controller.buttonColors.get(controller.btnGreen));
        assertEquals("#FFFF00", controller.buttonColors.get(controller.btnYellow));
        assertEquals("guitar.wav", controller.selectedSound);
    }

    @Test
    void testHandlePlayerInput_CorrectInput() {
        // Initialisez les données nécessaires
        controller.sequence.add(controller.btnRed);
        controller.buttonSounds.put(controller.btnRed, "sound1.wav");
        controller.players = List.of("Player1");
        controller.currentPlayerIndex = 0;
        controller.scores.add(0);

        // Simulez une entrée correcte
        controller.handlePlayerInput(controller.btnRed);

        // Vérifiez que l'entrée est correcte
        assertEquals(1, controller.playerInput.size(), "La taille des entrées doit être 1.");
        assertEquals(controller.btnRed, controller.playerInput.get(0), "Le bouton ajouté doit correspondre.");
        assertEquals("sound1.wav", controller.currentPlayerSounds.get(0), "Le son joué doit correspondre.");
        assertEquals("Joueur: Player1 - Score: 1", controller.lblScore.getText(), "Le score doit être mis à jour.");
    }

    @Test
    void testHandlePlayerInput_WrongInput() {
        // Initialisez la séquence avec une étape
        controller.sequence.add(controller.btnRed);

        // Ajoutez une vérification pour s'assurer que la séquence n'est pas vide
        assertFalse(controller.sequence.isEmpty(), "La séquence ne doit pas être vide.");

        // Donnez une mauvaise entrée
        controller.handlePlayerInput(controller.btnBlue);

        // Vérifiez que le joueur perd
        assertTrue(controller.playerInput.isEmpty(), "Les entrées du joueur doivent être vidées après une erreur.");
        assertTrue(controller.lblScore.getText().contains("a perdu"), "Le label doit indiquer que le joueur a perdu.");
    }

    @Test
    void testReplayBestPlayer_NoBestPlayer() {
        controller.bestPlayerSounds.clear(); // Aucune partie sauvegardée

        controller.replayBestPlayer();

        // Vérifiez que le message approprié est affiché
        assertEquals("Aucune partie à rejouer !", controller.lblScore.getText());
    }

    @Test
    void testReplayBestPlayer_WithBestPlayer() {
        controller.bestPlayerName = "Player1";
        controller.bestPlayerSounds = List.of("sound1.wav", "sound2.wav");

        controller.replayBestPlayer();

        // Vérifiez que le message approprié est affiché
        assertEquals("Rejoue la partie du meilleur joueur : Player1", controller.lblScore.getText());
    }

    @Test
    void testAddNextStep() {
        int initialSize = controller.sequence.size();

        controller.addNextStep();

        // Vérifiez qu'une nouvelle étape est ajoutée
        assertEquals(initialSize + 1, controller.sequence.size());
    }

    @Test
    void testPlaySequence() {
        Platform.runLater(() -> {
            controller.sequence.add(controller.btnRed);
            controller.sequence.add(controller.btnBlue);

            controller.playSequence();
        });
        WaitForAsyncUtils.waitForFxEvents(); // Attendez la fin des événements JavaFX

        // Vérifiez que la séquence est jouée
        assertEquals(2, controller.sequence.size());
    }

    @Test
    void testResetAndRestartGame() {
        controller.setPlayers(List.of("Player1", "Player2"));
        controller.startGame();

        controller.resetAndRestartGame();

        // Vérifiez que le jeu est réinitialisé correctement
        assertEquals(0, controller.sequence.size());
        assertEquals(0, controller.playerInput.size());
        assertEquals(0, controller.currentPlayerIndex);
    }
}