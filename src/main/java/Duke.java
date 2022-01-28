import duke.command.Command;
import duke.common.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Runs a Personal Assistant Chat bot.
 */
public class Duke {

    /** Storage handles loading and saving of tasks. */
    private Storage storage;

    /** Stores the list of tasks. */
    private TaskList tasks;

    /** Responsible for interactions with the user. */
    private Ui ui;

    /** File path used for storage. */
    private static final String FILE_PATH = ".\\data\\duke.txt";

    /**
     * Constructor to create Duke and load task from
     * storage.
     *
     * @param filePath file path of task data file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs and manages Duke chat bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
        ui.closeScanner();
    }


    /**
     *
     * Calls the run function for Duke chat bot.
     *
     * @param args command line arguments from terminal.
     */
    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }

}
