package fr.esgi.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SonServiceImplTest {

    private SonServiceImpl sonService;

    @BeforeEach
    void setUp() {
        sonService = new SonServiceImpl();
    }

    @Test
    void testPlaySound() {
        // Vérifie que playSound ne lève pas d'exception
        assertDoesNotThrow(() -> sonService.playSound("testSound"));
    }

    @Test
    void testStopSound() {
        // Vérifie que stopSound ne lève pas d'exception
        assertDoesNotThrow(() -> sonService.stopSound());
    }
}