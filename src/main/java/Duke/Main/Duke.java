package Duke.Main;

import Duke.Commands.Command;
import Duke.Commands.CommandEmpty;
import Duke.DukeException.DukeException;
import Duke.System.Parser;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreeting();
        ui.showLine();
        ui.showTutorial();
        ui.showLine();
        boolean isExitCommand = false;
        do {
            Command c = new CommandEmpty();
            try {
                String fullCommand = ui.promptCommand();
                c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException e) {
                ui.showException(e);
            } finally {
                ui.showLine();
            }
            isExitCommand = c.isExit();
        } while (!isExitCommand);
    }

    public static void main(String[] args) {
        new Duke("data").run();
    }
}
