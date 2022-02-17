package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a Duke chatbot.
 */
public class Duke {

    protected Storage storage;
    protected Ui ui;
    protected TaskList taskList;

    /**
     * Constructor for a Duke chatbot.
     */
    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.ui = new Ui();
        this.taskList = this.storage.readFromFile();
    }

    public String getResponse(String fullCommand) {
        String response = "";
        try {
            Command command = Parser.parse(fullCommand, this.taskList);
            response = command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
        return response;
    }
}
