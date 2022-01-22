import Command.*;
import DukeUtils.*;

import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private TaskList tasks;
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
                isExit = ExitCommand.isExit;
            } catch (CortanaException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        String path = Paths.get("").toAbsolutePath() + "/data/";
        new Duke(path).run();
    }
}