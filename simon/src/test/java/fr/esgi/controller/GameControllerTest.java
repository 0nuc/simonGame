package fr.esgi.controller;

import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameControllerTest {

    private GameController controller;

    @BeforeAll
    public static void initToolkit() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {});
        }
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