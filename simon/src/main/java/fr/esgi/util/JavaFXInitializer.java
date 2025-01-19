package fr.esgi.util;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;

public class JavaFXInitializer {

    private static boolean initialized = false;

    public static void initToolkit() {
        if (initialized) {
            return;
        }
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        try {
            latch.await();
            initialized = true;
        } catch (InterruptedException e) {
            throw new RuntimeException("Failed to initialize JavaFX Toolkit", e);
        }
    }
}