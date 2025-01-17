// package fr.esgi.business;

// import fr.esgi.service.JeuService;
// import fr.esgi.service.SonService;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;

// import java.util.List;

// import static org.mockito.Mockito.*;

// class JeuTest {
// @Mock
// List<Joueur> joueurs;
// @Mock
// JeuService jeuService;
// @Mock
// SonService sonService;
// @InjectMocks
// Jeu jeu;

// @BeforeEach
// void setUp() {
// MockitoAnnotations.initMocks(this);
// }

// @Test
// void testStart() {
// jeu.start();
// verify(jeuService).startGame();
// verify(sonService).playSound(anyString());
// }

// @Test
// void testEnd() {
// jeu.end();
// verify(jeuService).endGame();
// verify(sonService).stopSound();
// }
// }

// //Generated with love by TestMe :) Please raise issues & feature requests at:
// https://weirddev.com/forum#!/testme