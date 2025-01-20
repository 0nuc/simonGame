package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class CreditsController {

    @FXML
    private Button btnRetour;

    @FXML
    public void initialize() {
        // Ajout d'un gestionnaire d'événements pour le bouton "RETOUR"
        btnRetour.setOnAction(event -> handleRetour());
    }

    private void handleRetour() {
        // Charger la scène précédente (par exemple, le menu principal)
        try {
            // Remplacez "Menu.fxml" par le nom de votre fichier FXML pour le menu principal
            Parent root = FXMLLoader.load(getClass().getResource("/fr/esgi/primary.fxml"));
            Stage stage = (Stage) btnRetour.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}