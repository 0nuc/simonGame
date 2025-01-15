package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Button btnSolo;  // Bouton pour le mode solo

    @FXML
    private Button multijoueurButton;  // Bouton pour le mode multijoueur

    @FXML
    private Button btnCredits;  // Bouton pour les crédits

    @FXML
    private void handleMultijoueurClick() throws Exception {
        // Charger le fichier FXML pour l'écran secondaire
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/secondary.fxml"));

        // Récupérer la scène et le stage actuel à partir du bouton cliqué
        Stage stage = (Stage) multijoueurButton.getScene().getWindow();

        // Charger et afficher la nouvelle scène
        stage.setScene(new Scene(loader.load()));
        stage.show();
    }

    @FXML
    private void handleSoloClick() {
        try {
            // Charger le fichier FXML pour la page playerName
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/playerName.fxml"));
            Stage stage = (Stage) btnSolo.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            // Récupérer le contrôleur de PlayerName
            PlayerNameController playerNameController = loader.getController();
            playerNameController.setNumberOfPlayers(1);  // Passer 1 joueur

            // Afficher la nouvelle scène
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCreditsClick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/credits.fxml"));
            Stage stage = (Stage) btnCredits.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}