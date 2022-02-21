package duke;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Ace (Duke), a program that manages tasks and todos for the user.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Duke task manager.
     *
     * @param filepath The filepath in which the task list is stored and retrieved from.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    /**
     * Parses the user's input and generates the proper response.
     *
     * @param input The user's input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
