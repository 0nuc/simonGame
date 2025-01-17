package fr.esgi.controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameController {

    @FXML
    Button btnRed;

    @FXML
    Button btnBlue;

    @FXML
    Button btnGreen;

    @FXML
    Button btnYellow;

    @FXML
    Button btnStart;

    @FXML
    Button btnRestart;

    @FXML
    Label lblScore;

    List<Button> sequence = new ArrayList<>();
    private List<Button> playerInput = new ArrayList<>();
    private Random random = new Random();
    private List<String> players = new ArrayList<>();
    private final List<Integer> scores = new ArrayList<>();
    private int currentPlayerIndex = 0;
    private int totalPlayers = 1;
    private int currentStep = 0;

    public void initialize() {
        btnStart.setOnAction(event -> startGame());
        btnRestart.setOnAction(event -> resetAndRestartGame());

        btnRestart.setDisable(true);
        btnRestart.setVisible(false);

        btnRed.setOnAction(event -> handlePlayerInput(btnRed));
        btnBlue.setOnAction(event -> handlePlayerInput(btnBlue));
        btnGreen.setOnAction(event -> handlePlayerInput(btnGreen));
        btnYellow.setOnAction(event -> handlePlayerInput(btnYellow));
    }

    void startGame() {
        if (players.isEmpty()) {
            lblScore.setText("Aucun joueur enregistré !");
            return;
        }

        if (currentPlayerIndex >= players.size()) {
            lblScore.setText("Erreur : joueur invalide !");
            return;
        }

        sequence.clear();
        playerInput.clear();
        currentStep = 0;

        lblScore.setText("Joueur: " + players.get(currentPlayerIndex) + " - Score: 0");
        addNextStep();
        playSequence();
    }

    public void setPlayers(List<String> playerNames) {
        players.clear();
        scores.clear();

        players.addAll(playerNames);
        for (int i = 0; i < playerNames.size(); i++) {
            scores.add(0);
        }

        totalPlayers = players.size();
        currentPlayerIndex = 0;
    }

    private void addNextStep() {
        int index = random.nextInt(4);
        Button nextButton = getNextButton(index);
        sequence.add(nextButton);
    }

    private Button getNextButton(int index) {
        switch (index) {
            case 0:
                return btnRed;
            case 1:
                return btnBlue;
            case 2:
                return btnGreen;
            case 3:
                return btnYellow;
            default:
                throw new IllegalStateException("Unexpected value: " + index);
        }
    }

    private void playSequence() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        playStep(0, pause);
    }

    private void playStep(int index, PauseTransition previousPause) {
        if (index < sequence.size()) {
            Button button = sequence.get(index);
            previousPause.setOnFinished(event -> {
                highlightButton(button);
                PauseTransition nextPause = new PauseTransition(Duration.seconds(1));
                playStep(index + 1, nextPause);
            });
            previousPause.play();
        }
    }

    private void highlightButton(Button button) {
        String originalColor = button.getStyle();
        button.setStyle("-fx-background-color: white;");
        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
        pause.setOnFinished(event -> button.setStyle(originalColor));
        pause.play();
    }

    void handlePlayerInput(Button button) {
        if (button == sequence.get(playerInput.size())) {
            playerInput.add(button);
            if (playerInput.size() == sequence.size()) {
                playerInput.clear();
                currentStep++;
                scores.set(currentPlayerIndex, currentStep);
                lblScore.setText("Joueur: " + players.get(currentPlayerIndex) + " - Score: " + currentStep);
                addNextStep();
                playSequence();
            }
        } else {
            lblScore.setText(
                    "Joueur " + players.get(currentPlayerIndex) + " a perdu avec un score de " + currentStep + " !");
            scores.set(currentPlayerIndex, currentStep);
            nextPlayer();
        }
    }

    private void nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex >= totalPlayers) {
            showRanking();
        } else {
            startGame();
        }
    }

    private void showRanking() {
        List<PlayerScore> ranking = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            ranking.add(new PlayerScore(players.get(i), scores.get(i)));
        }

        ranking.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));

        StringBuilder rankingMessage = new StringBuilder("Classement Final :\n");
        for (int i = 0; i < ranking.size(); i++) {
            rankingMessage.append((i + 1)).append(". ").append(ranking.get(i).getName())
                    .append(" - Score: ").append(ranking.get(i).getScore()).append("\n");
        }

        lblScore.setText(rankingMessage.toString());

        btnRestart.setDisable(false);
        btnRestart.setVisible(true);

        btnRed.setDisable(true);
        btnBlue.setDisable(true);
        btnGreen.setDisable(true);
        btnYellow.setDisable(true);
    }

    void resetAndRestartGame() {
        System.out.println("Rejouer : Réinitialisation du jeu...");
        resetGame();

        scores.clear();
        for (int i = 0; i < players.size(); i++) {
            scores.add(0);
        }

        currentPlayerIndex = 0;

        btnRestart.setDisable(true);
        btnRestart.setVisible(false);

        btnRed.setDisable(false);
        btnBlue.setDisable(false);
        btnGreen.setDisable(false);
        btnYellow.setDisable(false);

        startGame();
    }

    private void resetGame() {
        sequence.clear();
        playerInput.clear();
        currentStep = 0;
    }

    private static class PlayerScore {
        private final String name;
        private final int score;

        public PlayerScore(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
}
