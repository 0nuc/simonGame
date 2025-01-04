package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerNameController {

    @FXML
    private VBox playerNameFields;  // VBox pour accueillir les champs de texte

    @FXML
    private Button btnRetour;  // Bouton retour

    @FXML
    private Button btnSuivant;  // Bouton suivant

    public void initialize() {
        // Action pour le bouton retour
        btnRetour.setOnAction(event -> {
            try {
                // Charger la scène secondaire
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/secondary.fxml"));
                Stage stage = (Stage) btnRetour.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Action pour le bouton suivant
        btnSuivant.setOnAction(event -> {
            try {
                // Charger la page de jeu
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/gamePage.fxml"));
                Stage stage = (Stage) btnSuivant.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        playerNameFields.getChildren().clear();  // Effacer les anciens champs

        // Définir les couleurs de bordure pour chaque joueur
        String[] borderColors = {"green", "blue", "red", "yellow"};

        // Afficher le nombre approprié de champs de texte
        for (int i = 1; i <= numberOfPlayers; i++) {
            TextField textField = new TextField();
            textField.setPromptText("Joueur " + i);
            textField.setPrefWidth(400);  // Largeur préférée
            textField.setPrefHeight(50);   // Hauteur préférée
            // Appliquer la couleur de bordure en fonction de l'index
            String borderColor = (i - 1 < borderColors.length) ? borderColors[i - 1] : "black"; // Utiliser "black" par défaut si plus de 4 joueurs
            textField.setStyle("-fx-background-color: none; -fx-border-width: 0px 0px 3px; -fx-border-color: " + borderColor + ";");
            playerNameFields.getChildren().add(textField);
        }
    }
}