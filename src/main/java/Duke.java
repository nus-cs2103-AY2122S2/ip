import duke.command.Command;
import duke.command.DukeException;
import duke.command.ExitCommand;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.lang.String;

public class Duke {

    TaskList tasklist;
    Ui ui;
    Storage storage;

    // Constructor
    public Duke(String filePath) {
        this.ui = new Ui();
        this.tasklist = new TaskList();
        this.storage = new Storage(filePath);
        storage.load(tasklist);
    }

    public void run() {
        Ui ui = new Ui();
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasklist, ui, storage);
                storage.save(tasklist);
                if (c instanceof ExitCommand) {
                    isExit = true;
                }
            } catch (DukeException e) {
                ui.printException(e);
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}