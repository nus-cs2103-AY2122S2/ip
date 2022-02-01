import duke.view.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches Abby.
     * @param args args is the input arguments of the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
