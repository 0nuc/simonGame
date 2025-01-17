package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    Button multijoueurButton;  // Un bouton dans votre fichier FXML (par exemple un bouton "Multijoueur")


    @FXML
    void handleMultijoueurClick() throws Exception {
        // Charger le fichier FXML pour l'écran secondaire
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/secondary.fxml"));

        // Récupérer la scène et le stage actuel à partir du bouton cliqué
        Stage stage = (Stage) multijoueurButton.getScene().getWindow();

        // Charger et afficher la nouvelle scène
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }
}
