package duke;

import duke.command.Command;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import duke.utils.Parser;
import duke.utils.CortanaException;

import java.nio.file.Paths;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFile());
    }

    public void run() {
        boolean isExit = false;
        ui.showWelcome();

        while(!isExit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CortanaException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        String PATH = Paths.get("").toAbsolutePath() + "/data/";
        new Duke(PATH).run();
    }
}