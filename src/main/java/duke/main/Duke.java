package duke.main;

import java.io.IOException;

import duke.commands.Command;
import duke.common.DukeException;
import duke.constants.Constants;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Acts as a task manager that keeps tracks of all your tasks.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Creates a Duke object that initializes all the necessary components for the task manager program.
     */
    public Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage(Constants.FILE_PATH + Constants.FILE_NAME);

        try {
            this.taskList = new TaskList(storage.loadFromFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates a response to user input.
     *
     * @param input input is the user input keyed into the input field.
     * @return Returns the response from the system back to the user.
     */
    public String getResponse(String input) {
        assert input != null : "Duke[getResponse] input cannot be null.";
        assert input.length() > 0 : "Duke[getResponse] input must contain data.";

        String response;

        try {
            Parser parser = new Parser(input);
            Command c = parser.parse();

            response = c.execute(taskList, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        return response;
    }

}
