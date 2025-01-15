package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreditsController {

    @FXML
    private Button btnRetour;  // Bouton retour

    @FXML
    public void initialize() {
        // Action pour le bouton retour
        btnRetour.setOnAction(event -> {
            try {
                // Charger la page principale
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/primary.fxml"));
                Stage stage = (Stage) btnRetour.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}