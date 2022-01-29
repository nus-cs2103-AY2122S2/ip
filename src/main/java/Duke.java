import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.command.Command;
import duke.Ui;

public class Duke {

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.read());
        } catch (DukeException e) {
            ui.showError(e.toString());
            tasks = new TaskList();
        }
    }

    private void run() {
        ui.showWelcome();
        boolean hasExit = false;
        while(!hasExit) {
            // default case in Parser::parseCommand handles invalid commands
            String fullCommand = ui.readCommand();
            if (fullCommand.isBlank()) {
                continue;
            }
            Command cmd = Parser.parseCommand(fullCommand);
            cmd.execute(tasks, ui, storage);
            hasExit = cmd.isExit();
        }
    }

    public static void main(String[] args) {
        new Duke("data/todo.dat").run();
    }

}
