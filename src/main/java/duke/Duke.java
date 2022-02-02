package duke;

import java.io.IOException;

import duke.command.Command;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Duke is a Personal Assistant Chatbot that helps a user to keep track of various things.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor class.
     *
     * @param filePath The filepath to the text file to save tasks.
     * @param fileName The name of the text file.
     */
    public Duke(String filePath, String fileName) {
        ui = new Ui();
        storage = new Storage(filePath, fileName);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the program.
     */
    public void run() {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                ui.showLine(); // show the divider line ("―――――")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/", "duke.txt").run();
    }
}


