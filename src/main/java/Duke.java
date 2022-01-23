import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Duke class represents a Personal Assistant Chatbot that helps a person to keep track of various tasks.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a Duke chatbot with the required objects, and loads up the data from the storage file.
     *
     * @param filePath Path of the file containing data.
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
     * Runs the chatbot until termination. Reads the user command and executes it,
     * until the user issues the exit command.
     */
    public void run() {
        ui.printGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
                Command command = Parser.parseCommand(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.printMessage(e.toString());
            }
        }

        try {
            storage.save(tasks);
        } catch (Exception e) {
            ui.printMessage(e.toString());
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}