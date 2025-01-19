package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;
import fr.esgi.business.Joueur;

public class PlayerNameController {

    private List<Joueur> joueurs = new ArrayList<>();

    @FXML
    private VBox playerNameFields;

    public void setNumberOfPlayers(int numberOfPlayers) {
        playerNameFields.getChildren().clear();

        for (int i = 1; i <= numberOfPlayers; i++) {
            TextField textField = new TextField();
            textField.setPromptText("Prénom du joueur " + i);
            textField.setPrefWidth(400);  // Largeur préférée
            textField.setPrefHeight(50);   // Hauteur préférée
            textField.setStyle("-fx-background-color: white; -fx-border-color: #007bff; -fx-border-width: 2px; -fx-border-radius: 5px;"); // Style du champ de texte
            playerNameFields.getChildren().add(textField);
        }
    }

    @FXML
    public void savePlayerNames() {
        joueurs.clear();
        for (javafx.scene.Node node : playerNameFields.getChildren()) {
            if (node instanceof TextField) {
                TextField textField = (TextField) node;
                String prenom = textField.getText();
                if (!prenom.isEmpty()) {
                    joueurs.add(new Joueur(prenom));
                }
            }
        }
        for (Joueur joueur : joueurs) {
            System.out.println("Player: " + joueur.getPrenom());
        }
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }
}