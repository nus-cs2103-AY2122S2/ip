package duke.main;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Behaves as a task manager to keep record of the different tasks.
 */
public class Duke {

    private final Parser parser;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Creates a Duke object that takes in a filepath for reading and writing of data.
     *
     * Initializes all necessary classes.
     * @param filePath Path in which the list of task is stored.
     */
    public Duke(String filePath) {
        parser = new Parser();
        storage = new Storage(filePath);
        taskList = new TaskList();
        taskList.fetchData(storage.readData());
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        assert input != null : "Input from user to get a response cannot be null.";
        assert input.length() > 0 : "Input from user to get a response cannot be empty.";

        String response = parser.parse(input);

        return "Duke: \n\n" + response;
    }

    /**
     * Saves the current data in a text file.
     */
    public void saveData() {
        storage.saveData(taskList.getList());
    }
}
