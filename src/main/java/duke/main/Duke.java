package duke.main;

import duke.command.*;
import duke.task.*;
import duke.ui.*;
import duke.storage.*;
import duke.parser.*;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Duke is a chat-bot that curates a todo list according to user's commands.
 */
public class Duke {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

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
            } catch (DateTimeParseException e){
                ui.showError("Your date and times have not been formatted properly.\n");
            } finally {
                ui.showLine();
            }
        }
    }
}
