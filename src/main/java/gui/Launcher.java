package gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Jeffry Lum
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
 * with minor modifications
 * @version CS2103T AY21/22 Sem 2
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}