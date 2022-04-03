package Duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        System.out.println("hello start");

//        Application.launch(Duke.class, args);
        Application.launch(Main.class, args);
    }
}