package fr.esgi.business;

public class Joueur {
    private String prenom;
    private int score;

    public Joueur(String prenom) {
        this.prenom = prenom;
        this.score = 0;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        this.score++;
    }
}