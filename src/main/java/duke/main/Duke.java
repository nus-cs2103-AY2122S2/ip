package duke.main;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Duke is a chat-bot that curates a todo list according to user's commands.
 */
public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    /**
     * Constructor for Duke class
     * @param filePath Path for file where data is stored and loaded
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * Start running the Duke program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showError("You have no task with that number.\n");
            } catch (DateTimeParseException e) {
                ui.showError("Your date and times have not been formatted properly.\n");
            } finally {
                ui.showLine();
            }
        }
    }
}
