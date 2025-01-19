package fr.esgi;

import fr.esgi.util.JavaFXInitializer;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AppTest {

    @BeforeAll
    static void initJavaFX() {
        // Initialiser JavaFX avant les tests
        JavaFXInitializer.initToolkit();
    }

    @Test
    void testStart() {
        // Test de démarrage de l'application
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                App app = new App();
                app.start(stage);

                Scene scene = stage.getScene();
                assertNotNull(scene, "La scène ne devrait pas être null après le démarrage.");
                assertNotNull(scene.getRoot(), "La racine de la scène ne devrait pas être null.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testSetRoot_ValidFXML() {
        // Test pour définir une racine valide
        Platform.runLater(() -> {
            try {
                Stage stage = new Stage();
                App app = new App();
                app.start(stage);

                App.setRoot("primary"); // Charger un fichier FXML valide
                Parent root = stage.getScene().getRoot();

                assertNotNull(root, "La nouvelle racine ne devrait pas être null après le chargement.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testSetRoot_InvalidFXML() {
        // Test pour charger un fichier FXML invalide
        assertThrows(IOException.class, () -> App.setRoot("invalid"), "Devrait lancer une exception pour un FXML invalide.");
    }

    @Test
    void testLoadFXML_Valid() {
        // Test de chargement d'un fichier FXML valide
        Platform.runLater(() -> {
            try {
                Parent root = (Parent) App.class.getDeclaredMethod("loadFXML", String.class)
                        .invoke(null, "primary");

                assertNotNull(root, "Le fichier FXML valide devrait être chargé correctement.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void testLoadFXML_Invalid() {
        // Test pour charger un fichier FXML invalide
        assertThrows(IOException.class, () -> {
            App.class.getDeclaredMethod("loadFXML", String.class).invoke(null, "invalid");
        }, "Devrait lancer une exception pour un fichier FXML invalide.");
    }
}