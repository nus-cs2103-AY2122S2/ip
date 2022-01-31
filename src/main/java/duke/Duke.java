package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.IOException;


/**
 * Represents the main class that Ekud will run on.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) throws DukeException, IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFileContents());
            ui.showLoadFileMessage(tasks);
        } catch (DukeException e) {
            ui.showLoadingErrorMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Run the Ekud programme.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException err) {
                System.err.println(err);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) throws IOException, DukeException {
        new Duke("data/ekud.txt").run();
    }
}
