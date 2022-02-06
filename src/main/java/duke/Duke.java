package duke;

import java.io.IOException;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A chatbot that helps manage tasks of user.
 */
public class Duke {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    /**
     * Starts up the Duke Bot in a specified filePath. If filePath
     * exists, then load the previously saved TaskList. Else, create a new one.
     *
     * @param filePath the specified filePath in user's computer downloads.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) { //abstract to duke.exception.DukeException
            ui.showError("Error loading file.");
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot application.
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // shows the divider line ("__________")
                ui.divide();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.divide();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
