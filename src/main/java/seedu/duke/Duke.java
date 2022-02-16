package seedu.duke;

import seedu.commands.Command;
import seedu.storage.Storage;
import seedu.storage.TaskList;

/**
 * Runner class for the application
 */
public class Duke {

    private TaskList tasks;
    private Parser parser;
    private Storage storage;

    /**
     * Constructor
     *
     * @param filePath file path of the save file
     */
    public Duke(String filePath) {
        parser = new Parser();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Bridge between gui and the application
     *
     * @param input The user input
     * @return The response of the application
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            String resp = c.execute(tasks);
            storage.saveAll(tasks.getTasks());
            return resp;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
