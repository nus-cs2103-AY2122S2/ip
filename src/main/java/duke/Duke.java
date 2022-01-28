package duke;

import duke.command.Command;
import duke.helptool.DukeException;
import duke.helptool.Parser;
import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;



/**
 * The type Duke.
 *
 * @author Dai Tianle
 * @version 1.0
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Create a chatterbot with record file located at filePath
     *
     * @param filePath The file's storage path
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showExceptionError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Run the chatterbot
     */
    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                if (c != null) {
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                }
            } catch (DukeException e) {
                ui.showExceptionError(e);
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
        new Duke("./textRecord.txt").run();
    }
}
