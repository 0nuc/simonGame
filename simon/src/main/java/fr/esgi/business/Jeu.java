package fr.esgi.business;

import fr.esgi.service.JeuService;
import fr.esgi.service.SonService;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Data
public class Jeu {

    private List<Joueur> joueurs;

    private JeuService jeuService;

    private SonService sonService;

    public Jeu(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(joueurs, jeuService, sonService);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Jeu other = (Jeu) obj;
        return Objects.equals(joueurs, other.joueurs) &&
                Objects.equals(jeuService, other.jeuService) &&
                Objects.equals(sonService, other.sonService);
    }

    @Override
    public String toString() {
        return "Jeu [joueurs=" + joueurs +
                ", jeuService=" + jeuService +
                ", sonService=" + sonService + "]";
    }

}
