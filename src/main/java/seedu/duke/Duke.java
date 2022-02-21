package seedu.duke;

import seedu.command.Command;
import seedu.exception.DukeException;

/**
 * Stores private instances of Storage and TaskList objects.
 * Contains methods to instantiate the object instances and run the command inputs.
 * Handles the result from the parsed commands to print out relevant results back to the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final String FILE_PATH = "taskHistory.txt";

    /**
     * Instantiates the Storage and TaskList objects.
     * Stores the tasks loaded into the TaskList object and creates a new TaskList object otherwise.
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
     * Calls Parser class methods to parse in the user inputs to create Command subclass
     * objects in order to execute and run the given command.
     *
     * @param input User input entered.
     * @return String result from the run command.
     */
    public String getResponse(String input) {
        assert input != null : "Duke->getResponse: Input string cannot be null.";

        try {
            Command command = new Parser(input).getCommand();
            return command.run(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
