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

    public static void main (String[] args) {
        new Duke("tasks.txt").run();
    }

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
