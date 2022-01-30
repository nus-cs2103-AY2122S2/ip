package duke.main;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.functionality.Parser;
import duke.functionality.Storage;
import duke.functionality.TaskList;

/**
 * Represents the starting point of the Duke project. A <code> Duke </code> object corresponds
 * to a TaskBot that helps keep tracks of all tasks inputted by a user.
 */
public class Duke {
    protected Storage storage;
    protected TaskList taskList;

    /**
     * Default constructor for Duke class.
     */
    public Duke() {
        this.taskList = new TaskList(); //default task list
    }

    /**
     * Returns nothing, but is used to initialize Storage by reading in existing data from the
     * text file specified by filePath.
     * @param pwd user's current working directory.
     * @param filePath filePath path to "/data/TaskData.txt".
     * @throws IOException if the file is missing.
     */
    public void initializeStorage(String pwd, String filePath) throws IOException {
        this.storage = new Storage(pwd, filePath);
        this.taskList = storage.load();
    }

    /**
     * Returns a message that corresponds to the action taken by the input of the user.
     * @param input user's input.
     * @return message that is crafted by executing the respective command.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command command = Parser.parse(input);
            output = command.execute(taskList);
            Storage.updateTextFile(taskList);
            return output;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
