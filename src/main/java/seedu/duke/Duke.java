package seedu.duke;

import seedu.commands.Command;
import seedu.storage.Storage;
import seedu.storage.TaskList;

public class Duke {

    private final Ui ui;
    private TaskList tasks;
    private final Parser parser;
    private final Storage storage;

    /**
     * Constructor for Duke class
     * @param filePath The file path of the save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runner for the program.
     * Saves task list into save file for every command run.
     * Stops if command == "bye".
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                storage.saveAll(tasks.getTasks());
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
