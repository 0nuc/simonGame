package fr.esgi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class JeuServiceTest {

    private JeuService jeuService;

    @BeforeEach
    void setUp() {
        jeuService = mock(JeuService.class);
    }

    @Test
    void testStartGame() {
        doNothing().when(jeuService).startGame();
        jeuService.startGame();
        verify(jeuService, times(1)).startGame();
    }

    @Test
    void testEndGame() {
        doNothing().when(jeuService).endGame();
        jeuService.endGame();
        verify(jeuService, times(1)).endGame();
    }
}