import Commands.DukeCommand;
import Exceptions.DukeException;
import Tasks.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

import java.io.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e.getMessage());
        }

    }
    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();

                DukeCommand c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ui.showLine();
            }
        }
    }
}
