package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Initializes and starts the duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Sets up the required objects for the program.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(storage.retrieveTaskList());
    }

    /**
     * Generates a response to user input.
     *
     * @param input The full user input string.
     * @return Duke's response to the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
