package fr.esgi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class SonServiceTest {

    private SonService sonService;

    @BeforeEach
    void setUp() {
        // Création d'un mock de l'interface SonService
        sonService = Mockito.mock(SonService.class);
    }

    @Test
    void testPlaySound() {
        // Appeler la méthode playSound sur le mock
        sonService.playSound("testSound");

        // Vérifier que la méthode a été appelée avec le bon argument
        verify(sonService, times(1)).playSound("testSound");
    }

    @Test
    void testStopSound() {
        // Appeler la méthode stopSound sur le mock
        sonService.stopSound();

        // Vérifier que la méthode a été appelée une fois
        verify(sonService, times(1)).stopSound();
    }

    @Test
    void testPlaySoundAndStopSoundSequence() {
        // Simuler une séquence d'appels
        sonService.playSound("testSound");
        sonService.stopSound();

        // Vérifier que playSound a été appelé avant stopSound
        InOrder inOrder = inOrder(sonService);
        inOrder.verify(sonService).playSound("testSound");
        inOrder.verify(sonService).stopSound();
    }
}