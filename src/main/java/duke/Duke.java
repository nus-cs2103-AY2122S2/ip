package duke;
import duke.exceptions.DukeException;
import duke.functionality.Parser;
import duke.functionality.Storage;
import duke.functionality.TaskList;
import duke.ui.UserInterface;

/**
 * Duke is the main class that executes the logic of the chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates the Duke object.
     *
     * @param filePath The String filePath.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        try {
            storage.readFile(this.tasks);
        } catch (DukeException e) {
            UserInterface.errorMessage(e);
        }
    }

    public String getResponse(String input) {
        String output = "";
        try {
            output = output + Parser.parse(input, this.tasks);
            this.storage.writeFile(this.tasks);
        } catch (DukeException errorMesssage) {
            output = output + UserInterface.errorMessage(errorMesssage);
        }
        return output;
    }
}
