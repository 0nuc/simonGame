package fr.esgi.service.impl;

import fr.esgi.service.SonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SonServiceImplTest {

    private SonService sonService;

    @BeforeEach
    void setUp() {
        sonService = (SonService) new SonServiceImpl();
    }

    @Test
    void testPlaySound() {
        sonService.playSound("testSound");
    }

    @Test
    void testStopSound() {
        sonService.stopSound();
    }
}