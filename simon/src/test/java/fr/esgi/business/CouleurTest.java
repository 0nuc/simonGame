package fr.esgi.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouleurTest {

    @Test
    void testEnumValues() {
        // Vérifie que les valeurs de l'énumération sont correctes
        Couleur[] couleurs = Couleur.values();
        assertArrayEquals(new Couleur[]{Couleur.ROUGE, Couleur.VERT, Couleur.BLEU, Couleur.JAUNE}, couleurs);
    }

    @Test
    void testEnumValueOf() {
        // Vérifie que valueOf retourne correctement une valeur de l'énumération
        assertEquals(Couleur.ROUGE, Couleur.valueOf("ROUGE"));
        assertEquals(Couleur.VERT, Couleur.valueOf("VERT"));
        assertEquals(Couleur.BLEU, Couleur.valueOf("BLEU"));
        assertEquals(Couleur.JAUNE, Couleur.valueOf("JAUNE"));
    }

    @Test
    void testEnumName() {
        // Vérifie que le nom de l'énumération correspond bien
        assertEquals("ROUGE", Couleur.ROUGE.name());
        assertEquals("VERT", Couleur.VERT.name());
        assertEquals("BLEU", Couleur.BLEU.name());
        assertEquals("JAUNE", Couleur.JAUNE.name());
    }
}