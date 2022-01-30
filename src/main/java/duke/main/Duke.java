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
     *
     * @param filePath File path from where to load the saved tasks.
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
     * Runs the Duke application
     */

    public void run() {
        this.ui.printBootUp();
        boolean isExit = false;

        // Program will keep taking in new user input until terminated
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                this.ui.printLineSeparator();
                Command command = Parser.parse(fullCommand);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.printException(e);
            } finally {
                this.ui.printLineSeparator();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/data/save.txt").run();
    }
}
