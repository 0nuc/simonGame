package fr.esgi.business;

import fr.esgi.service.JeuService;
import fr.esgi.service.SonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JeuTest {

    @Mock
    private JeuService jeuService;

    @Mock
    private SonService sonService;

    private Jeu jeu;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jeu = new Jeu();
        jeu.setJoueurs(Arrays.asList(new Joueur("Alice"), new Joueur("Bob")));
        jeu.setJeuService(jeuService);
        jeu.setSonService(sonService);
    }

    @Test
    void testStart() {
        // Appelle la méthode start
        jeu.start();

        // Vérifie que les services sont appelés correctement
        verify(jeuService, times(1)).startGame();
        verify(sonService, times(1)).playSound("startSound");
    }

    @Test
    void testEnd() {
        // Appelle la méthode end
        jeu.end();

        // Vérifie que les services sont appelés correctement
        verify(jeuService, times(1)).endGame();
        verify(sonService, times(1)).stopSound();
    }

    @Test
    void testGetJoueurs() {
        // Vérifie que la liste des joueurs est correctement retournée
        List<Joueur> joueurs = jeu.getJoueurs();
        assertEquals(2, joueurs.size());
        assertEquals("Alice", joueurs.get(0).getPrenom());
        assertEquals("Bob", joueurs.get(1).getPrenom());
    }

    @Test
    void testSetJoueurs() {
        // Change la liste des joueurs
        List<Joueur> nouveauxJoueurs = Arrays.asList(new Joueur("Charlie"), new Joueur("Dana"));
        jeu.setJoueurs(nouveauxJoueurs);

        // Vérifie que la liste est mise à jour
        List<Joueur> joueurs = jeu.getJoueurs();
        assertEquals(2, joueurs.size());
        assertEquals("Charlie", joueurs.get(0).getPrenom());
        assertEquals("Dana", joueurs.get(1).getPrenom());
    }
}