package luca;

import javafx.application.Platform;
import luca.command.Command;
import luca.common.DukeException;
import luca.parser.Parser;
import luca.storage.Storage;
import luca.task.TaskList;

/**
 * Runs a Personal Assistant Chat bot.
 */
public class Luca {

    /** Handles loading and saving of tasks. */
    private Storage storage;

    /** Temporary store of list of tasks. */
    private TaskList tasks;

    /**
     * Constructor to create duke.Duke and load task from
     * storage.
     *
     * @param filePath file path of task data file.
     */
    public Luca(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Parses the user input, creates and executes a command.
     * Outputs the response string accordingly.
     *
     * @param input user input.
     * @return response string.
     */
    public String getResponse(String input) {
        String response;
        try {
            String fullCommand = input;
            Command command = Parser.parse(fullCommand);
            response = command.execute(tasks, storage);
        } catch (DukeException exception) {
            response = exception.getMessage();
        }
        return response;
    }

}
