package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import tasks.TaskList;

/**
 * Represents a Duke chat bot
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final TextUi ui;
    private final Parser parser = new Parser();


    /**
     * Instantiates a duke.Duke object with a directoryPath and filePath of the storage file
     * @param directoryPath directoryPath to storage file
     * @param filePath filePath to storage file
     */
    public Duke(String directoryPath, String filePath) {
        TaskList tasks1;
        this.ui = new TextUi();
        this.storage = new Storage(directoryPath, filePath);
        try {
            tasks1 = new TaskList(storage.readFromDukeFile());
        } catch (DukeException e) {
            tasks1 = new TaskList();
        }
        this.tasks = tasks1;
    }

    /**
     * Method that returns the response from running the Duke Program
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parseInput(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}


