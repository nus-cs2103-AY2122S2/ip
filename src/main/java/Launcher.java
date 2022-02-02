import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /** Launch the app.
     *
     * @param args variables to launch app
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
