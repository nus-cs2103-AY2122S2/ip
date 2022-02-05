package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.task.Storage;
import duke.task.TaskList;
import duke.gui.Ui;

/**
 * Represents a Duke chatbot.
 * A Duke chatbot will save or load the user's tasks in a local file.
 */

public class Duke {

    private TaskList taskList;
    private String userName;
    private String taskFilePath;
    private Ui ui;
    private Storage storage;
    private String dukeResponse;

    /**
     * Constructor to initialize Duke & its various components.
     *
     * @param filePath Path of savefile of tasks information
     * @return Nothing.
     */
    public Duke(String filePath) {
        taskFilePath = filePath;
        ui = new Ui();
        storage = new Storage(taskFilePath, ui);
        // load TaskList from existing data
        taskList = new TaskList(storage.loadFileContents());
    }

    /**
     * Reads user's input & executes corresponding commands.
     * @return
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            dukeResponse = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            dukeResponse = e.getMessage();
        }
        return dukeResponse;
    }

}
