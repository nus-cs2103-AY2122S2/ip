package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Represents a Duke chat bot
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final TextUi ui;
    private final Parser parser = new Parser();


    /**
     * Instantiates a Duke chat bot.
     * It requires a directory path and file path to locate the storage file.
     * The storage file contains the tasks that the user has keyed in before.
     *
     * @param directoryPath Directory path to storage file.
     * @param filePath File path to storage file.
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
     * Method that returns the response from running the Duke Program.
     * This response could either be a success string that has executed what the user
     * has typed in or an error message if the program is unable to run the text
     * that the user has keyed in.
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


