package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

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

    public Duke() {
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

    /** Obtains Tasks from provided text file */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }

    public String getResponse(String userInput) throws DukeException, IOException {
        return ui.parseUserInput(tasks, storage, userInput);
    }
}
