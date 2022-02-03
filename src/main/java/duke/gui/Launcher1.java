package duke.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher1 {
    /**
     * does something.
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(SampleGui1.class, args);
        //Application.launch(Main.class, args);
    }
}
