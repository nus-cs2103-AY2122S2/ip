import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Runs entire application.
     * @param args Command line argument for main.
     */
    public static void main(String[] args) {
        //Launches the Nexus application with a GUI.
        Application.launch(Main.class, args);
    }
}
