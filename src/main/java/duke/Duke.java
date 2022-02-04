package duke;

import duke.command.Command;
import duke.command.InvalidCommand;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * It represents the Duke task list.
 */
public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Parser parser;
    private boolean shouldAbort;

    /**
     * Constructor for duke.
     * @param filepath the relative path to store task list on disk.
     */
    public Duke(String filepath) {
        this.shouldAbort = false;
        this.storage = new Storage(filepath);
        this.tasks = new TaskList(storage.load());
        this.parser = new Parser();
    }

    /**
     * The main method for the duke task list.
     * Creates an instance of duke and runs it.
     * @param args the arguments that are given when the program is run.
     */
    public static void main(String[] args) {
        Duke chatBot = new Duke("data/data.txt");
    }

    public boolean hasAborted() {
        return this.shouldAbort;
    }

    public String getResponse(String input) {
        String response;
        try {
            Command command = parser.parseCommand(input);
            response = command.exec(tasks, storage);
            shouldAbort = command.shouldAbort();
        } catch (DukeException e) {
            response = new InvalidCommand(e.toString()).exec(tasks, storage);
        }
        return response;
    }
}
