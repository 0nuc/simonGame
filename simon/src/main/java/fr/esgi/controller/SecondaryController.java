package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class SecondaryController {

    @FXML
    private Circle circle2;  // Lié à circle2 dans le FXML

    @FXML
    private Circle circle3;  // Lié à circle3 dans le FXML

    @FXML
    private Circle circle4;  // Lié à circle4 dans le FXML

    @FXML
    public void initialize() {
        // Ajouter des événements de clic pour chaque cercle
        circle2.setOnMouseClicked(event -> openPlayerNameScreen(2));
        circle3.setOnMouseClicked(event -> openPlayerNameScreen(3));
        circle4.setOnMouseClicked(event -> openPlayerNameScreen(4));
    }

    private void openPlayerNameScreen(int numberOfPlayers) {
        try {
            // Charger le fichier FXML pour playerName.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/playerName.fxml"));

            // Obtenir l'élément de la scène actuelle pour récupérer le Stage
            Stage stage = (Stage) circle2.getScene().getWindow();  // Récupérer la fenêtre actuelle à partir de circle2
            stage.setScene(new Scene(loader.load()));

            // Obtenir le contrôleur de playerName.fxml et passer le nombre de joueurs
            PlayerNameController controller = loader.getController();
            controller.setNumberOfPlayers(numberOfPlayers);  // Passer le nombre de joueurs

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
