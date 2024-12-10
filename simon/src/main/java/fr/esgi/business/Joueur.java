package fr.esgi.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder
public class Joueur{

    private String prenom;
    private int score;

    public Joueur() {
        this.score = 0; // Score initial par défaut
    }

    public Joueur(String prenom) {
        this.prenom = prenom;
        this.score = 0;
    }

    public Joueur(String prenom, int score) {
        this.prenom = prenom;
        this.score = score;
    }

    public void incrementScore() {
        this.score++;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenom, score);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Joueur other = (Joueur) obj;
        return Objects.equals(prenom, other.prenom) && score == other.score;
    }

    @Override
    public String toString() {
        return "Joueur [prenom=" + prenom + ", score=" + score + "]";
    }
}