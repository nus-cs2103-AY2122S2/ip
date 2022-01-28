package duke;

import duke.info.exception.DukeException;
import duke.info.task.Calendar;

import duke.commands.Command;

import duke.parser.Parser;

import duke.storage.Storage;

import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private Calendar calendar;
    private Ui ui;

    /**
     * Runs the Duke program using the run() method specified.
     * @param args - command line arguments
     */
    public static void main (String[] args) {
        new Duke("tasks.txt").run();
    }

    /**
     * Constructs a Duke object with a new Ui and storage handler. The filePath
     * will be used to load a previous save using the storage handler, but if
     * no previous save exists a new Calendar will be created
     * @param filePath - the file path for the previous save file.
     */

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.calendar = new Calendar(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.calendar = new Calendar();
        }
    }

    /**
     * Executes the Duke program.
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
