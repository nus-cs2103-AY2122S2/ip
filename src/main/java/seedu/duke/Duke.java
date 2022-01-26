package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ListCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.NoCommandException;
import seedu.duke.exceptions.NoDateException;
import seedu.duke.task.*;

import java.io.IOException;

/**
 * Functions as the chatbot by taking in inputs.
 * Also helps in giving out specified outputs.
 */
//path: "C:\\Users\\isabe\\IdeaProjects\\ip-false\\src\\data\\oldTasks.txt"
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, ui);
        try {
            this.taskList = storage.getOldTaskList();
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

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

    public static void main(String[] args) {
        String filePath = "C:\\Users\\isabe\\IdeaProjects\\ip-false\\src\\data\\oldTasks.txt";
        new Duke(filePath).run();
    }
}