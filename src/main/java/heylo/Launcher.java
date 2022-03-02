// Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
package heylo;

import heylo.ui.App;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(App.class, args);
    }
}