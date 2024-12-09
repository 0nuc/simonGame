package fr.esgi.business;

import fr.esgi.service.JeuService;
import fr.esgi.service.SonService;

import java.util.List;

public class Jeu {
    private List<Joueur> joueurs;
    private JeuService jeuService;
    private SonService sonService;

    public Jeu(List<Joueur> joueurs, JeuService jeuService, SonService sonService) {
        this.joueurs = joueurs;
        this.jeuService = jeuService;
        this.sonService = sonService;
    }

    public void start() {
        jeuService.startGame();
        sonService.playSound("startSound");
    }

    public void end() {
        jeuService.endGame();
        sonService.stopSound();
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
}