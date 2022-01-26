package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.manager.Parser;
import duke.manager.Storage;
import duke.manager.TaskList;
import duke.manager.Ui;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.parser = new Parser();
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

}
