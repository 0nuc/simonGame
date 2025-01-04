package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PlayerNameController {

    @FXML
    private VBox playerNameFields; // VBox pour accueillir les champs de texte

    @FXML
    private Button btnRetour; // Bouton retour

    @FXML
    private Button btnSuivant; // Bouton suivant

    private int numberOfPlayers = 2; // Par défaut, deux joueurs

    public void initialize() {
        // Charger les champs pour le nombre de joueurs par défaut
        loadPlayerNameFields();

        // Action pour le bouton retour
        btnRetour.setOnAction(event -> {
            try {
                // Charger la scène précédente
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
                // Récupérer les noms des joueurs
                List<String> playerNames = getPlayerNames();

                if (playerNames.isEmpty()) {
                    System.out.println("Erreur : Aucun nom de joueur renseigné !");
                    return;
                }

                // Charger la page de jeu
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/esgi/gamePage.fxml"));
                Stage stage = (Stage) btnSuivant.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));

                // Passer les noms des joueurs au GameController
                GameController gameController = loader.getController();
                gameController.setPlayers(playerNames);

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void loadPlayerNameFields() {
        playerNameFields.getChildren().clear(); // Effacer les anciens champs

        // Définir les couleurs de bordure pour chaque joueur
        String[] borderColors = { "green", "blue", "red", "yellow" };

        // Afficher le nombre approprié de champs de texte
        for (int i = 1; i <= numberOfPlayers; i++) {
            TextField textField = new TextField();
            textField.setPromptText("Nom du Joueur " + i);
            textField.setPrefWidth(400); // Largeur préférée
            textField.setPrefHeight(50); // Hauteur préférée

            // Appliquer la couleur de bordure en fonction de l'index
            String borderColor = (i - 1 < borderColors.length) ? borderColors[i - 1] : "black";
            textField.setStyle("-fx-background-color: none; -fx-border-width: 0px 0px 3px; -fx-border-color: "
                    + borderColor + ";");

            playerNameFields.getChildren().add(textField);
        }
    }

    private List<String> getPlayerNames() {
        List<String> playerNames = new ArrayList<>();
        for (var child : playerNameFields.getChildren()) {
            if (child instanceof TextField) {
                String name = ((TextField) child).getText().trim();
                if (!name.isEmpty()) {
                    playerNames.add(name);
                }
            }
        }
        return playerNames;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
        loadPlayerNameFields();
    }
}
