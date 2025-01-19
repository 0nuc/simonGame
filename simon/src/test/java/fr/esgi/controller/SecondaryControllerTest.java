package fr.esgi.controller;

import fr.esgi.util.JavaFXInitializer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SecondaryControllerTest {

    private SecondaryController controller;

    @BeforeAll
    static void initJavaFX() {
        JavaFXInitializer.initToolkit(); // Initialiser JavaFX avant tous les tests
    }

    @Test
    void testOpenPlayerNameScreenFor2Players() throws Exception {
        controller = new SecondaryController();

        FXMLLoader loader = mock(FXMLLoader.class);
        Stage stage = mock(Stage.class);

        when(loader.load()).thenReturn(new VBox());
        controller.circle2 = new javafx.scene.shape.Circle(); // Simuler `circle2`

        // Simuler le clic sur le cercle
        controller.openPlayerNameScreen(2);

        verify(stage, never()).show();
    }
}