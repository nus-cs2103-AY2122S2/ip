package duke;

import duke.command.Command;
import duke.command.ByeCommand;

import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.io.FileNotFoundException;

/**
 * Represents a chat-bot program named Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke chat-bot program which initialises with a saved file (if applicable), list of tasks, and system Ui.
     */
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

    /**
     * Runs the Duke chat-bot program and takes in inputs
     */
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