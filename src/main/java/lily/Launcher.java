package lily;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the Lily GUI
     * @param args String input
     */
    public static void main(String[] args) {
        Application.launch(Lily.class, args);
    }
}