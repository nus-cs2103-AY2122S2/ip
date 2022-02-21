package duke;

import java.io.IOException;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * A chatbot that helps manage tasks of user.
 */
public class Duke {

    //the specified filePath in user's computer downloads.
    private static final String filePath = "data/duke.txt";
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    /** Interprets messages sent by the user */
    private final Parser parser;

    /**
     * Starts up the Duke Bot in a specified filePath. If filePath
     * exists, then load the previously saved TaskList. Else, create a new one.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser(ui);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) { //abstract to duke.exception.DukeException
            ui.showError("Error loading file.");
            this.tasks = new TaskList();
        }
    }

    /**
     * Gets the response to the user input.
     */
    public String getResponse(String input) {
        return parser.parse(input, tasks, storage);
    }

}
