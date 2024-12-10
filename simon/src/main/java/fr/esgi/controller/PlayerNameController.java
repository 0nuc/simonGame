package fr.esgi.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PlayerNameController {

    @FXML
    private VBox playerNameFields;

    public void setNumberOfPlayers(int numberOfPlayers) {
        playerNameFields.getChildren().clear();

        for (int i = 1; i <= numberOfPlayers; i++) {
            TextField textField = new TextField();
            textField.setPromptText("Prénom du joueur " + i);
            playerNameFields.getChildren().add(textField);
        }
    }
}
