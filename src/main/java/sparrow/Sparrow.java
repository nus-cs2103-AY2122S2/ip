package sparrow;

import sparrow.commons.exceptions.ParseException;
import sparrow.commons.exceptions.SparrowException;
import sparrow.logic.commands.Command;
import sparrow.logic.parser.CommandParser;
import sparrow.model.TaskList;
import sparrow.storage.Storage;
import sparrow.ui.Ui;

/**
 * Represents Sparrow.
 */
public class Sparrow {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Duke object.
     *
     * @param filePath The file path to where tasks should be saved.
     */
    public Sparrow(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (SparrowException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Produces the welcome message.
     *
     * @return The welcome message.
     */
    public String welcome() {
        return ui.welcome(tasks);
    }

    /**
     * Parses the user's input and generates the proper response.
     *
     * @param input The user's input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command command = CommandParser.parse(input);
            return command.execute(storage, tasks, ui);
        } catch (ParseException | SparrowException e) {
            return e.getMessage();
        }
    }
}
