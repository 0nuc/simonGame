package fr.esgi.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JeuServiceImplTest {

    private JeuServiceImpl jeuService;

    @BeforeEach
    void setUp() {
        jeuService = new JeuServiceImpl();
    }

    @Test
    void testStartGame() {
        // Capture de la sortie standard pour vérifier l'exécution
        assertDoesNotThrow(() -> {
            jeuService.startGame();
        });
    }

    @Test
    void testEndGame() {
        // Capture de la sortie standard pour vérifier l'exécution
        assertDoesNotThrow(() -> {
            jeuService.endGame();
        });
    }
}