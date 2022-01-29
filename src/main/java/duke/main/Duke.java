package duke.main;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.operations.Parser;
import duke.operations.Storage;
import duke.operations.TaskList;
import duke.operations.Ui;

import java.io.IOException;

/**
 * Represents a Duke bot to record tasks.
 *
 * @author Zachary Chan
 * @version 0.1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * A Duke constructor to initialise a <code>Duke</code> object.
     *
     * @param currentWorkingDirectory the current working directory of the user.
     * @param filePath the filePath to the txt file.
     */
    public Duke(String currentWorkingDirectory, String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, currentWorkingDirectory);
        try {
            this.tasks = new TaskList();
            storage.load();
        } catch (IOException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the application and starts the interaction with the user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showDukeError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /**
     * Initialises a <code>Duke</code> object and calls run().
     *
     * @param args none.
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        new Duke(home, "/DukeSaveDirectory/DukeSaveFile.txt").run();
    }
}
