package duke;

import java.nio.file.Paths;

import duke.command.Command;
import duke.utils.CortanaException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Duke the todolist helper, it's actual name is Cortana in this particular implementation.
 */
public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path for storing/retrieving the todolist
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    /**
     * Run Duke.
     */
    public void run() {
        boolean isExit = false;
        ui.showWelcome();

        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CortanaException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String path = Paths.get("").toAbsolutePath() + "/data/";
        new Duke(path).run();
    }
}
