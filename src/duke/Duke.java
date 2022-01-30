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
    private TaskList tasks = new TaskList(); //has items in it
    private Ui ui;

    /**
     * Constructor.
     *
     * @param filePath (required) Filepath of a text file which retrieves or saves Tasks
     * generated from this application.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, tasks);
        try {
            storage.readTasksFromFile();
            storage.closeReadFile();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read provided file!\n");
        } catch (IOException e) {
            System.out.println("Unable to close file that is read!\n");
        }
    }

    /** Starts up the simple display of Duke application.  */
    public void run() throws DukeException, IOException {
        boolean isTerminated = false;
        while (!isTerminated) {
            ui.displayCommandMessage(tasks, storage);
        }
    }

    /** Obtains Tasks from provided text file */
    public static void main(String[] args) throws DukeException, IOException {
        //"C:\\NUS\\CS2103\\iP\\data\\duke.txt"
        new Duke("data/duke.txt").run();
    }
}
