package fr.esgi.business;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Joueur {

    private String prenom;
    private int score;

    public Joueur(String prenom) {
        this.prenom = prenom;
        this.score = 0;
    }

    public void incrementScore() {
        this.score++;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenom, score);
    }
}