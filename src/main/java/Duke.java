import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke chatbot with the required objects, and loads up the data from the storage file.
     *
     * @param filePath Path of the file containing the stored tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    /**
     * Gets user input and executes the corresponding command.
     *
     * @param input User input.
     * @return Result of the executed command.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parseCommand(input.trim());
            if (command.isExit()) {
                storage.save(tasks);
            }
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return ui.showMessage(e.toString());
        }
    }
}
