package duke;

import duke.commands.Command;
import duke.info.exception.DukeException;
import duke.info.task.Calendar;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {

    private Storage storage;
    private Calendar calendar;
    private Ui ui;
    private String filePath = "tasks.txt";

    /**
     * Constructs a Duke object with a new Ui and storage handler. The filePath is set
     * as "tasks.txt", and will be used to load a previous save, but a new Calendar will
     * be created if no previous save exists.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.calendar = new Calendar(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.calendar = new Calendar();
        }
    };

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(calendar, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs the Duke program using the run() method specified.
     * @param args - command line arguments
     */
    public static void main (String[] args) {
        new Duke().run();
    }

    /**
     * Executes the Duke Program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show divider line
                Command c = Parser.parse(fullCommand);
                c.execute(calendar, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


}
