package fr.esgi.business;

import fr.esgi.service.JeuService;
import fr.esgi.service.SonService;
import fr.esgi.service.impl.JeuServiceImpl;
import fr.esgi.service.impl.SonServiceImpl;

import java.util.List;

public class Jeu {
    private List<Joueur> joueurs;
    private JeuService jeuService;
    private SonService sonService;

    public Jeu(List<Joueur> joueurs) {
        this.joueurs = joueurs;
        this.jeuService = new JeuServiceImpl();
        this.sonService = new SonServiceImpl();
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