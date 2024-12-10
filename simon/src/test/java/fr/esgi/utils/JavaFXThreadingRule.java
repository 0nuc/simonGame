package fr.esgi.utils;

import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;

public class JavaFXThreadingRule {

    private static boolean initialized = false;

    public static void initToolkit() {
        if (initialized) {
            return; // Avoid reinitializing
        }

        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        try {
            latch.await();
            initialized = true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
