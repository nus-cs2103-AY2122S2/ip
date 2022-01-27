package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Class containing Duke and main function
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.taskList = new TaskList();
    }


    public void run() {
        ui.showHi();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand().toLowerCase();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}