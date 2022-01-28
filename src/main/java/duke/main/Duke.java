package duke.main;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.command.Command;
import duke.parser.Parser;

/**
 * Represents Duke main class
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs duke program until exit command is made
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e ) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Instantiates Duke and run it
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
