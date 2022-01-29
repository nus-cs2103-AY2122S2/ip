package duke.main;

import java.io.IOException;

import duke.commands.Command;
import duke.common.DukeException;
import duke.constants.Constants;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Acts as a task manager that keeps tracks of all your tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates a Duke object that initializes all the necessary components for the task manager program.
     * @param filePath filePath is the relative path to the text file that stores user's tasks.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);

        try {
            this.taskList = new TaskList(storage.loadFromFile());
        } catch (IOException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs, handles and processes the commands input by the user.
     */
    public void run() {
        ui.hello();

        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Parser parser = new Parser(fullCommand);
                Command c = parser.parse();

                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.output(e.getMessage());
            }
        }
    }

    /**
     * Boots up the entire task manager application.
     * @param args Args is the command line arguments received from user.
     */
    public static void main(String[] args) {
        new Duke(Constants.FILE_PATH + Constants.FILE_NAME).run();
    }
}
