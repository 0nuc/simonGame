package fr.esgi.service.impl;

import fr.esgi.service.JeuService;

public class JeuServiceImpl implements JeuService {

    @Override
    public void startGame() {
        // Implémentation pour démarrer le jeu
        System.out.println("Game started");
    }

    @Override
    public void endGame() {
        // Implémentation pour terminer le jeu
        System.out.println("Game ended");
    }
}