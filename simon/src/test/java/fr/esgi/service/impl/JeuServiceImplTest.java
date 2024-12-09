package fr.esgi.service.impl;

import fr.esgi.service.JeuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JeuServiceImplTest {

    private JeuService jeuService;

    @BeforeEach
    void setUp() {
        jeuService = new JeuServiceImpl();
    }

    @Test
    void testStartGame() {
        jeuService.startGame();
        // Ajoutez des assertions pour vérifier le comportement
    }

    @Test
    void testEndGame() {
        jeuService.endGame();
        // Ajoutez des assertions pour vérifier le comportement
    }
}