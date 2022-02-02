package seedu.duke.chatbot;

import seedu.duke.command.Command;
import seedu.duke.exceptions.DukeException;
import seedu.duke.task.TaskList;

import java.io.IOException;

/**
 * Functions as the chatbot by taking in inputs.
 * Also helps in giving out specified outputs.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Creates the chatbot.
     * @param filePath which provides the path to the database, a txt file storing old tasks
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, ui);
        try {
            this.taskList = storage.getOldTaskList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Starts the chatbot by taking in commands and executing them.
     */
    public void run() {
        String name  = ui.showWelcome();
        Parser parser = new Parser(name);
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand); //read the full command and return the command
                this.taskList = c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Runs the chatbot Duke with a specified path to the database file.
     */
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")  + "/data/OldTasks.txt";
        new Duke(filePath).run();
    }
}