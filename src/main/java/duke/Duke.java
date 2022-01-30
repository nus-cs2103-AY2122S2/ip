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
    private UserInterface userInterface;

    /**
     * Creates the Duke object.
     *
     * @param filePath The String filePath.
     */
    public Duke(String filePath) {
        this.userInterface = new UserInterface();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList();
        try {
            storage.readFile(this.tasks);
        } catch (DukeException e) {
            userInterface.errorMessage(e);
        }
    }

    /**
     * Runs the logic of the application.
     */
    public void run() {
        userInterface.introMessage();
        String input = userInterface.readInput();
        while (!input.equals("bye")) {
            try {
                userInterface.lineDivider();
                Parser.parse(input, this.tasks);
                this.storage.writeFile(this.tasks);
                userInterface.lineDivider();
            } catch (DukeException errorMessage) {
                userInterface.errorMessage(errorMessage);
            }
            input = userInterface.readInput();
        }
        userInterface.byeMessage();
    }

    /**
     * Executes the running of the application.
     *
     * @param args The main method arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
