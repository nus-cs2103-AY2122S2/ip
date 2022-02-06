package mickey.app;

import mickey.command.Command;
import mickey.task.TaskList;

/**
 * Mickey task manager
 */
public class Mickey {

    /** Storage to save and load previous tasks. */
    private final Storage storage;

    /** TaskList to store tasks. */
    private TaskList tasks;

    /** Ui to generate terminal output. */
    private final Ui ui;

    /** Path of storage file. */
    private static final String filePath = "src/main/data/save.txt";

    /**
     * Constructor.
     */
    public Mickey() {
        ui = new Ui();
        storage = new Storage(Mickey.filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Mickey program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MickeyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (MickeyException e) {
            return e.toString();
        }
    }
}