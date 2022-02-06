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

    public Duke() {
        //for JavaFx to run
    }
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
            System.out.println(ui.showError(e.getMessage()));
        }
    }

    /**
     * Starts the chatbot by taking in commands and executing them.
     */
    public void run() {
        String name  = ui.showWelcome();
        Parser parser = new Parser();
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
    /*public static void main(String[] args) {
        String filePath = "C:\\Users\\isabe\\IdeaProjects\\ip-false\\src\\data\\oldTasks.txt";
        new Duke(filePath).run();
    }*/

    public String getResponse(String input) {
        try {
            Parser parser = new Parser();
            Command c = parser.parse(input); //read the full command and return the command
            this.taskList = c.execute(this.taskList, this.ui, this.storage);
            return c.getResponseAfterCommand(this.taskList);
        } catch (DukeException | IOException e) {
            return ui.showError(e.getMessage());
        }
    }
}