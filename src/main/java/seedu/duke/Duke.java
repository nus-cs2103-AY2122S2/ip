package seedu.duke;

import java.io.IOException;

import seedu.command.*;
import seedu.exception.DukeException;

/**
 * Includes the Main driver class.
 * Stores public static instances of storage, task and ui objects.
 * Contains methods to run and instantiate the object instances.
 * Handles the result from the parsed commands to print out different
 * results back to the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final String FILE_PATH = "taskHistory.txt";

    /**
     * Instantiates the storage and tasklist objects.
     * Stores the tasks loaded into the tasklist object.
     *
     */
    public Duke() {
        storage = new Storage(FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Shows the welcome line when Duke chat-bot starts running.
     * Interacts with user inputs by calling getNextInput() from ui class.
     * Calls Parser class methods to parse in the user inputs.
     * Exits the running chat-bot when the 'bye' command is read from user input.
     */
    public String getResponse(String input) throws DukeException {
        assert input != null : "Duke->getResponse: Input string cannot be null.";

        String nextInput = input;
        Command command = new Parser(nextInput).getCommand();
        try {
            return command.run(tasks, storage);
        } catch (DukeException error) {
            return "OOPS!!! " + error.getMessage();
        }
    }
}
