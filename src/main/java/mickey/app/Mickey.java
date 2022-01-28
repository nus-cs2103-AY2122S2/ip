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

    /**
     * Constructor.
     *
     * @param filePath Path of the file containing saved tasks.
     */
    public Mickey(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
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
     * @param args Arguments to be passed.
     */
    public static void main(String[] args) {
        new Mickey("src/main/data/tasks.txt").run();
    }
}