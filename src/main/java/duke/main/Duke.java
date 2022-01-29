package duke.main;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.functionality.Parser;
import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.functionality.Ui;

/**
 * Represents the starting point of the Duke project. A <code> Duke </code> object corresponds
 * to a TaskBot that helps keep tracks of all tasks inputted by a user.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke class.
     * @param pwd user's current working directory.
     * @param filePath path to "/data/TaskData.txt".
     */
    public Duke(String pwd, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(pwd, filePath);
        try {
            this.tasks = new TaskList();
            storage.load();
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    /**
     * This method is used to start the execution of the TaskBot. This method would continually ask users for input
     * until the user enters the ExitCommand. Eg, "Bye".
     */
    public void run() {
        this.ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showEnding();
    }

    /**
     * This is the main method which makes use of run method.
     * @param args unused.
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        new Duke(home, "/data/TaskData.txt").run();
    }
}
