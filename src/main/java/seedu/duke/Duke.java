package seedu.duke;

import seedu.commands.Command;
import seedu.storage.Storage;
import seedu.storage.TaskList;

/**
 * The main class of Duke
 */
public class Duke {

    private final Ui UI;
    private TaskList tasks;
    private final Parser PARSER;
    private final Storage STORAGE;

    /**
     * Constructor for Duke class
     *
     * @param filePath The file path of the save file.
     */
    public Duke(String filePath) {
        UI = new Ui();
        PARSER = new Parser();
        STORAGE = new Storage(filePath);

        try {
            tasks = new TaskList(STORAGE.load());
        } catch (DukeException e) {
            UI.showError(e.getMessage());
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
                String fullCommand = UI.readCommand();
                Command c = PARSER.parse(fullCommand);
                String resp = c.execute(tasks);
                STORAGE.saveAll(tasks.getTasks());
                UI.printCompleted(resp);
            } catch (DukeException e) {
                UI.showError(e.getMessage());
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
            Command c = PARSER.parse(input);
            String resp = c.execute(tasks);
            STORAGE.saveAll(tasks.getTasks());
            return resp;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
