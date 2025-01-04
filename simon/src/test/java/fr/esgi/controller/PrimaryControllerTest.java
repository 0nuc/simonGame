package fr.esgi.controller;

import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class PrimaryControllerTest {
    @Mock
    Button multijoueurButton;
    @InjectMocks
    PrimaryController primaryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme