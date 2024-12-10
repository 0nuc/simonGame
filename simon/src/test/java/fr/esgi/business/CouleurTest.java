package fr.esgi.business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouleurTest {

    @Test
    void testEnumValues() {
        Couleur[] valeursAttendues = {Couleur.ROUGE, Couleur.VERT, Couleur.BLEU, Couleur.JAUNE};
        Couleur[] valeursReelles = Couleur.values();
        assertArrayEquals(valeursAttendues, valeursReelles, "Les valeurs de l'énumération Couleur doivent correspondre.");
    }

    @Test
    void testEnumValueOf() {
        assertEquals(Couleur.ROUGE, Couleur.valueOf("ROUGE"), "La valeur ROUGE doit être accessible via valueOf.");
        assertEquals(Couleur.VERT, Couleur.valueOf("VERT"), "La valeur VERT doit être accessible via valueOf.");
        assertEquals(Couleur.BLEU, Couleur.valueOf("BLEU"), "La valeur BLEU doit être accessible via valueOf.");
        assertEquals(Couleur.JAUNE, Couleur.valueOf("JAUNE"), "La valeur JAUNE doit être accessible via valueOf.");
    }

    @Test
    void testEnumInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> Couleur.valueOf("NOIR"), "Une exception doit être levée pour une valeur non définie dans l'énumération.");
    }
}