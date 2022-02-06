package duke;

import java.util.Scanner;

/**
 * Main executable for the Duke program.
 * Helps user record the tasks to be completed and their deadlines.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            System.out.println(e);
            tasks = new TaskList();
        }

    }

    public void saveDuke() {
        storage.save(tasks);
    }

    public String startDuke() {
        return ui.getStartOutput();
    }

    public String getResponse(String input) {
        return ui.getDukeOutput(input, tasks);
    }

}
