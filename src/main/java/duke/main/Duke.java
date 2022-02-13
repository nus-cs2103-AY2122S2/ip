package duke.main;

import duke.command.Command;
import duke.exception.DukeException;
import duke.function.Parser;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;


/**
 * Duke is a Todo list command line application that allows you to create, delete, mark, and save tasks
 */

public class Duke {
    /**
     * To load and save tasks into the specified file path
     */
    private Storage storage;
    /**
     * To maintain the current list of tasks
     */
    private TaskList tasks;
    /**
     * To handle any input output interaction with users
     */
    private Ui ui;

    /**
     * Returns a Duke application that loads previously saved tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printException(e);
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(this.tasks, this.ui, this.storage);
        } catch (Exception e) {
            return e.toString();
        }
    }
}
