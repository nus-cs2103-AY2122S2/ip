package duke;

import java.io.IOException;

/**
 * Duke runs a task tracker to help keep track of tasks. Tasks can be marked as done or not done,
 * and can be added and deleted from the task list.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an instance of Duke.
     * @param filePath A string representing the path to the file to save tasks to.
     * @throws IOException if an I/O error occurs.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            if (storage.load().equals("")) {
                throw new DukeException("");
            }
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the task tracker.
     *
     * @throws IOException if an I/O error occurs.
     */
    public void run() throws IOException {
        ui.showGreeting();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            Command command = Parser.parse(fullCommand, ui);
            command.execute(tasks, ui, storage);
            isExit = command.isExit();
            ui.showLine();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}
