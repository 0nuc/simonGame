package fr.esgi.controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @FXML
    Button btnApplyConfig;
    @FXML
    ComboBox<String> colorRed;
    @FXML
    ComboBox<String> colorBlue;
    @FXML
    ComboBox<String> colorGreen;
    @FXML
    ComboBox<String> colorYellow;
    @FXML
    ComboBox<String> soundChoice;
    @FXML
    Button btnReplayBest;

    List<Button> sequence = new ArrayList<>();
    List<Button> playerInput = new ArrayList<>();
    private Random random = new Random();
    List<String> players = new ArrayList<>();
    final List<Integer> scores = new ArrayList<>();
    Map<Button, String> buttonColors = new HashMap<>();
    String selectedSound = "default.wav";
    int currentPlayerIndex = 0;
    private int totalPlayers = 1;
    private int currentStep = 0;
    final Map<Button, String> buttonSounds = new HashMap<>();
    private List<Button> bestPlayerSequence = new ArrayList<>();
    String bestPlayerName = "";
    final List<String> currentPlayerSounds = new ArrayList<>();
    List<String> bestPlayerSounds = new ArrayList<>();

    public void initialize() {
        btnReplayBest.setOnAction(event -> replayBestPlayer());
        btnReplayBest.setDisable(true);
        btnReplayBest.setVisible(false);
        List<String> colors = Arrays.asList("#FF0000", "#0000FF", "#008000", "#FFFF00", "#FFFFFF", "#000000");
        colorRed.getItems().addAll(colors);
        colorBlue.getItems().addAll(colors);
        colorGreen.getItems().addAll(colors);
        colorYellow.getItems().addAll(colors);

        buttonSounds.put(btnRed, "/sounds/guitar.wav");
        buttonSounds.put(btnBlue, "/sounds/piano.wav");
        buttonSounds.put(btnGreen, "/sounds/guitar.wav");
        buttonSounds.put(btnYellow, "/sounds/piano.wav");

        List<String> sounds = Arrays.asList("guitar.wav", "piano.wav");
        soundChoice.getItems().addAll(sounds);

        btnApplyConfig.setOnAction(event -> applyConfigurations());

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

    void applyConfigurations() {
        // Appliquer les couleurs choisies
        buttonColors.put(btnRed, colorRed.getValue() != null ? colorRed.getValue() : "#480E0E");
        buttonColors.put(btnBlue, colorBlue.getValue() != null ? colorBlue.getValue() : "#11164F");
        buttonColors.put(btnGreen, colorGreen.getValue() != null ? colorGreen.getValue() : "#0C5C15");
        buttonColors.put(btnYellow, colorYellow.getValue() != null ? colorYellow.getValue() : "#88840A");

        btnRed.setStyle("-fx-background-color: " + buttonColors.get(btnRed) + ";");
        btnBlue.setStyle("-fx-background-color: " + buttonColors.get(btnBlue) + ";");
        btnGreen.setStyle("-fx-background-color: " + buttonColors.get(btnGreen) + ";");
        btnYellow.setStyle("-fx-background-color: " + buttonColors.get(btnYellow) + ";");

        // Appliquer le son choisi
        selectedSound = soundChoice.getValue() != null ? soundChoice.getValue() : "default.wav";
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

    void addNextStep() {
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

    void playSequence() {
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
        // Vérifiez si la séquence est vide
        if (sequence.isEmpty()) {
            throw new IllegalStateException("La séquence est vide. Impossible de gérer l'entrée.");
        }

        // Vérifiez si l'index de playerInput est valide pour la séquence
        if (playerInput.size() >= sequence.size()) {
            throw new IndexOutOfBoundsException("Index invalide : " + playerInput.size() + ". Taille de la séquence : " + sequence.size());
        }

        // Vérifiez si le bouton correspond à l'étape actuelle de la séquence
        if (button == sequence.get(playerInput.size())) {
            playerInput.add(button);

            // Vérifiez si le bouton a un son associé
            String soundPath = buttonSounds.get(button);
            if (soundPath == null) {
                throw new IllegalStateException("Aucun son associé au bouton.");
            }

            currentPlayerSounds.add(soundPath);
            playSound(soundPath);

            // Si le joueur termine la séquence actuelle
            if (playerInput.size() == sequence.size()) {
                playerInput.clear();
                currentStep++;
                scores.set(currentPlayerIndex, currentStep);

                // Vérifiez si le joueur existe avant de mettre à jour le score
                if (currentPlayerIndex >= players.size()) {
                    throw new IllegalStateException("Index du joueur invalide : " + currentPlayerIndex);
                }
                lblScore.setText("Joueur: " + players.get(currentPlayerIndex) + " - Score: " + currentStep);

                // Ajoutez une nouvelle étape et rejouez la séquence
                addNextStep();
                playSequence();
            }
        } else {
            // Le joueur a fait une erreur
            if (currentPlayerIndex >= players.size()) {
                throw new IllegalStateException("Index du joueur invalide : " + currentPlayerIndex);
            }

            lblScore.setText(
                    "Joueur " + players.get(currentPlayerIndex) + " a perdu avec un score de " + currentStep + " !");
            scores.set(currentPlayerIndex, currentStep);

            saveBestPlayerIfNeeded();
            nextPlayer();
        }
    }

    private void saveBestPlayerIfNeeded() {
        int highestScore = scores.stream().max(Integer::compareTo).orElse(0);

        if (scores.get(currentPlayerIndex) >= highestScore) {
            bestPlayerName = players.get(currentPlayerIndex);
            bestPlayerSounds = new ArrayList<>(currentPlayerSounds);
        }

        currentPlayerSounds.clear();
    }

    private void playSound(String soundFile) {
        try {
            URL soundUrl = getClass().getResource(soundFile);
            if (soundUrl == null) {
                System.err.println("❌ Erreur: Fichier son introuvable -> " + soundFile);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(soundUrl.toURI()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nextPlayer() {
        btnReplayBest.setDisable(false);
        btnReplayBest.setVisible(true);
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

        if (!ranking.isEmpty()) {
            bestPlayerName = ranking.get(0).getName();
            bestPlayerSequence = new ArrayList<>(sequence);
        }

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
        btnReplayBest.setDisable(false);
        btnReplayBest.setVisible(true);
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

    void replayBestPlayer() {
        if (bestPlayerSounds.isEmpty()) {
            lblScore.setText("Aucune partie à rejouer !");
            return;
        }
        lblScore.setText("Rejoue la partie du meilleur joueur : " + bestPlayerName);
        replayStep(0);
    }

    private void replayStep(int index) {
        if (index < bestPlayerSounds.size()) {
            playSound(bestPlayerSounds.get(index));

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event -> replayStep(index + 1));
            pause.play();
        }
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