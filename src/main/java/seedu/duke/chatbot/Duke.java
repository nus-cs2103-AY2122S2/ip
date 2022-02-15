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
    private Parser parser;
    private static final String FILE_PATH = "data/";
    private static final String FILE_NAME = "duke.txt";

    /**
     * Creates the chatbot.
     */
    public Duke() {
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH, FILE_NAME);
        try {
            this.taskList = storage.getOldTaskList();
        } catch (DukeException e) {
            System.out.println(Ui.showError(e.getMessage()));
        }
    }

    /**
     * Returns a response to the user based on user input.
     * @param input which contains user input
     * @return a string response to be shown to user
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input); //read the full command and return the command
            this.taskList = c.execute(this.taskList, this.storage);
            return c.getResponseAfterCommand(this.taskList);
        } catch (DukeException | IOException e) {
            return Ui.showError(e.getMessage());
        }
    }
}