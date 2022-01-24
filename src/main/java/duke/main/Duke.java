package duke.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.function.Parser;
import duke.function.Storage;
import duke.function.TaskList;
import duke.function.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printException(e);
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws FileNotFoundException, IOException {
        this.ui.printBootUp();
        boolean isExit = false;

        // Program will keep taking in new user input until terminated
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                this.ui.printLineSeparator();
                Command command = Parser.parse(fullCommand);
                command.execute(this.tasks, this.ui, this.storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                this.ui.printException(e);
            } finally {
                this.ui.printLineSeparator();
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Duke("src/main/data/save.txt").run();
    }
}
