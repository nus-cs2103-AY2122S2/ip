package duke;
import javafx.application.Application;

/**
 * Launcher class to work aound class path problems
 */
public class Launcher {
    public static void main(String[] args) throws ExceptionHandler {
        Application.launch(Main.class, args);
    }
}
