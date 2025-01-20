package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class SecondaryController {

    @FXML
    Circle circle2;  // Lié à circle2 dans le FXML

    @FXML
    Circle circle3;  // Lié à circle3 dans le FXML

    @FXML
    Circle circle4;  // Lié à circle4 dans le FXML

    @FXML
    Button btnRetour;  // Bouton retour

    @FXML
    public void initialize() {
        // Ajouter des événements de clic pour chaque cercle
        circle2.setOnMouseClicked(event -> openPlayerNameScreen(2));
        circle3.setOnMouseClicked(event -> openPlayerNameScreen(3));
        circle4.setOnMouseClicked(event -> openPlayerNameScreen(4));

        // Action pour le bouton retour
        btnRetour.setOnAction(event -> handleRetour());
    }

    private void handleRetour() {
        try {
            // Charger la page principale
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/primary.fxml"));
            Stage stage = (Stage) btnRetour.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();  // Affiche l'erreur dans la console
        }
    }

    void openPlayerNameScreen(int numberOfPlayers) {
        try {
            // Charger le fichier FXML pour playerName.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/playerName.fxml"));

            // Obtenir le stage actuel
            Stage stage = (Stage) circle2.getScene().getWindow();  // Récupérer la fenêtre actuelle à partir de circle2
            stage.setScene(new Scene(loader.load()));

            // Obtenir le contrôleur de playerName.fxml et passer le nombre de joueurs
            PlayerNameController controller = loader.getController();
            controller.setNumberOfPlayers(numberOfPlayers);  // Passer le nombre de joueurs

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();  // Affiche l'erreur dans la console
        }
    }
}