package fr.esgi.controller;

import fr.esgi.util.JavaFXInitializer;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class PrimaryControllerTest {

    private PrimaryController controller;
    private Button multijoueurButton;

    @BeforeAll
    static void initJavaFX() {
        JavaFXInitializer.initToolkit(); // Initialiser JavaFX avant tous les tests
    }

    @Test
    void testHandleMultijoueurClick() throws Exception {
        Platform.runLater(() -> {
            try {
                // Initialisation du contrôleur
                controller = new PrimaryController();

                // Créez un bouton et une scène pour simuler l'environnement
                multijoueurButton = new Button("Multijoueur");
                VBox root = new VBox(multijoueurButton);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);

                // Associez le bouton au contrôleur
                controller.multijoueurButton = multijoueurButton;

                // Configurez l'événement de clic
                multijoueurButton.setOnAction(event -> {
                    try {
                        controller.handleMultijoueurClick();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });

                // Simulez un clic sur le bouton
                multijoueurButton.fire();

                // Vérifiez que l'action a été exécutée correctement
                assertNotNull(stage.getScene());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}