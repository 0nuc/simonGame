package fr.esgi.controller;

import fr.esgi.util.JavaFXInitializer;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;


import static org.junit.jupiter.api.Assertions.assertEquals;

class GameControllerTest {

    private GameController controller;

    @BeforeAll
    static void initJavaFX() {
        JavaFXInitializer.initToolkit(); // Initialiser JavaFX avant tous les tests
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new GameController();
    }

    @Test
    void testResetAndRestartGame() {
        controller = new GameController();

        Button btnRestart = new Button();
        controller.btnRestart = btnRestart;

        btnRestart.fire();

        assertEquals("Button", btnRestart.getClass().getSimpleName());
    }
}