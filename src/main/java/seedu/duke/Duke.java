package seedu.duke;

import seedu.commands.Command;
import seedu.storage.Storage;
import seedu.storage.TaskList;

/**
 * The main class of Duke
 */
public class Duke {

    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    private Storage storage;

    /**
     * Constructor for Duke class
     *
     * @param filePath The file path of the save file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        System.out.println("Welcome!\n");
    }

    /**
     * Runner for the program.
     * Saves task list into save file for every command run.
     * Stops if command == "bye".
     */
    public void run() {
        while (!Command.isExit()) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                String resp = c.execute(tasks);
                storage.saveAll(tasks.getTasks());
                ui.printCompleted(resp);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Used in the GUI to get responses from Duke
     *
     * @param input The command to be entered
     * @return The response of Duke
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            String resp = c.execute(tasks);
            storage.saveAll(tasks.getTasks());
            return resp;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
