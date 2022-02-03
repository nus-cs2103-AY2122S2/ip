import command.Command;
import exception.DukeException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Duke bot allows users to interact with a "smart" bot that tracks tasks,
 * deadlines, todos and events. Moreover, new features are progressively
 * added into the bot - caching, search, data validation.
 */
public class Duke extends Exception {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Starts an instance of the Duke bot.
     *
     * @param filePath location of the chat history file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Run the instance of the Duke bot.
     * Acts as the start point which encapsulates
     * the program logic.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Start point of the project
     *
     * @param args Default parameters string
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
