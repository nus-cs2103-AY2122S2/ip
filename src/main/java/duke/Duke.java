package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasks.TaskList;

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
     * Runs the program until exit is issued.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getUserInput();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
