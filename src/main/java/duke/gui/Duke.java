package duke.gui;

import java.io.IOException;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Duke helps the user manage their tasks.
 */
public class Duke {
    private static final String LOG_PATH = "data/log.txt";

    private boolean isExit;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        this(LOG_PATH);
    }

    /**
     * Constructor for Duke.
     * Prints welcome message, initialises storage and taskList.
     *
     * @param filePath Path to log file.
     */
    public Duke(String filePath) {
        isExit = false;
        try {
            storage = new Storage(filePath);
            taskList = new TaskList(storage.readTasks());
        } catch (DukeException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Takes in user input and prints result of parsing user input.
     *
     * @param input User's input.
     * @return String Result of parsing user input
     */
    public String getResponse(String input) {
        try {
            String output = Parser.parse(input, taskList);

            // Updates log file
            storage.updateTasks(taskList);

            if (input.equals("bye")) {
                isExit = true;
            }

            return output;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Prints welcome message upon starting Duke.
     *
     * @return Welcome message.
     */
    public String getWelcome() {
        String output = "GOOD MORNING CHAO RECRUIT! YOU MAY CALL ME ENCIK ENCIK"
                + "\nWHAT YOU WANT?";
        return output;
    }
}
