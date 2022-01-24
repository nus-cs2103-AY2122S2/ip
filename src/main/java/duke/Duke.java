package duke;

import duke.command.*;
import duke.helptool.*;

public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showExceptionError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                if (c!= null){
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

    public static void main(String[] args) {
        new Duke("./textRecord.txt").run();
    }
}
