package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;


/**
 * This is a Duke class that orchestrates the running of the Duke
 * task application.
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-30
 */
public class Duke {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private String filePath = "data/duke.txt";

    /**
     * Initializes Ui and Storage objects.
     * Loads information of Tasks from Duke text file into the Duke application
     */
    public Duke() {
        assertTrue(Files.exists(Path.of(filePath))); //assumes the file provided is a valid text file
        ui = new Ui();
        storage = new Storage(filePath, tasks);
        try {
            storage.tasksThatHaveBeenRead();
            storage.closeReadFile();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read provided file!\n");
        } catch (IOException e) {
            System.out.println("Unable to close file that is read!\n");
        }
    }

    /** Starts up the simple display of Duke application.  */
    public String run() throws DukeException, IOException {
        return ui.createWelcomeMessage();
    }

    /** Creates a Duke object and starts up the simple display of Duke application. */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }

    /**
     * Provides the response of Duke based on user's input to be shown in the application GUI
     * @param userInput is the command that user provides in the GUI
     */
    public String getResponse(String userInput) throws DukeException, IOException {
        return ui.parseUserInput(tasks, storage, userInput);
    }
}
