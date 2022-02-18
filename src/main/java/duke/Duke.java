package duke;

import duke.command.Commands;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents the main program of a bot named DockerHawker. The main program is represented
 * by a <code>Ui</code> object, a <code>TaskList</code> object, and a <code>Storage</code> object.
 * These objects serve to facilitate, parse, and execute valid inputs provided by the user
 */
public class Duke {
    private TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Sole constructor for an instance of the class Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        tasks = new TaskList(storage.load());
    }

    /**
     * Act as a bridge between the business logic of the code and the models. It parses commands from the
     * User Interface and returns a response based on the execution of the command.
     *
     * @param inputCommand Command provided by the user in the text input.
     * @return A string response resulted from the business logic of the code.
     */
    public String getResponse(String inputCommand) {
        Commands c = Parser.parse(inputCommand);
        tasks = new TaskList(storage.load());
        return c.execute(tasks, ui, storage).toString();
    }
}
