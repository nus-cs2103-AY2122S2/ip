package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.gui.Ui;
import duke.task.Storage;
import duke.task.TaskList;

/**
 * Represents a Duke chatbot.
 * A Duke chatbot will save or load the user's tasks in a local file.
 */

public class Duke {

    private TaskList tasksList;
    private String tasksFilePath;
    private Ui ui;
    private Storage storage;
    private String dukeResponse;

    /**
     * Constructor to initialize Duke & its various components.
     * @param filePath Path of savefile of tasks information
     */
    public Duke(String filePath) {
        assert filePath != null;
        tasksFilePath = filePath;
        ui = new Ui();
        storage = new Storage(tasksFilePath, ui);
        // load TaskList from existing data
        tasksList = new TaskList(storage.loadFileContents());
    }

    /**
     * Reads user's input & executes corresponding commands.
     * @return
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            dukeResponse = c.execute(tasksList, ui, storage);
        } catch (DukeException e) {
            dukeResponse = e.getMessage();
        }
        return dukeResponse;
    }

}
