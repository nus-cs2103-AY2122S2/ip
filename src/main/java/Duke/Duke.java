package Duke;

import Duke.command.Command;
import Duke.command.ByeCommand;

import Duke.exception.DukeException;

import Duke.util.Parser;
import Duke.util.Storage;
import Duke.util.TaskList;
import Duke.util.Ui;

import java.io.FileNotFoundException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try { // Load existing task-list
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                if (c instanceof ByeCommand) {
                    isExit = true;
                }
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (NullPointerException e) {
                ui.showError("\tI'm sorry matey, that's an invalid input. Please try again :'(\n");
            }
        }
    }

    public static void main(String[] args) {
        new Duke("src/main/java/Duke/data/duke.txt").run();
    }
}