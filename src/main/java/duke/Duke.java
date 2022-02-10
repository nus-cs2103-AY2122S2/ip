package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Duke is the main class that starts the application.
 */
public class Duke {
    protected static ArrayList<Task> list;

    /**
     * Main method to start Duke.
     * 
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Ui.welcome();

        try {
            list = LoadFile.load();

            if (!LoadFile.hasLoaded) {
                list = new ArrayList<>();
            }

            Parser.start();

        } catch (DukeException | IOException e) {
            System.err.print(e);
        } catch (DateTimeParseException e) {
            System.err.println("Oops! That was not a valid date format, please try again in the format yyyy-mm-dd!");
        }
    }
}
