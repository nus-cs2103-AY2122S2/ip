package duke;
import javafx.application.Application;

/**
 * Launcher class to workaound class path problems
 */
public class Launcher {
    public static void main(String[] args) throws ExceptionHandler {
        Application.launch(Main.class, args);
    }
}
