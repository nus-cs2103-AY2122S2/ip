package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Duke is an interactive bot that helps the user to note down important tasks.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskMaster taskMaster;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskMaster = new TaskMaster(storage.loadTasks());
    }

    /**
     * Generates a response to user input.
     * @param input that the user gave to the GUI
     * @return String to respond to the user's request
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command command = Parser.parse(input);
            response = command.execute(this.taskMaster, this.ui, this.storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }
        return response;
    }

}