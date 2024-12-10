package fr.esgi.service.impl;

import fr.esgi.service.SonService;

public class SonServiceImpl implements SonService {

    @Override
    public void playSound(String sound) {
        // Implémentation pour jouer un son
        System.out.println("Playing sound: " + sound);
    }

    @Override
    public void stopSound() {
        // Implémentation pour arrêter un son
        System.out.println("Stopping sound");
    }
}