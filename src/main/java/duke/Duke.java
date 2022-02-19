package duke;

import java.io.IOException;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an instance of Duke.
     * Sets up UI, Storage, TaskList tagged to instance of Duke.
     *
     * @param filePath Path where storage is located or to be created.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            ui.showTasksLoaded(tasks);
        } catch (IOException | ArrayIndexOutOfBoundsException e) {
            ui.showError("LoadingError");
            tasks = new TaskList();
        }
    }

    /**
     * Runs that instance of Duke.
     * Allows users to interact with Duke in CLI.
     */
    public void run() {
        ui.welcome();
        boolean end = false;
        while (!end) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand, ui);
                c.execute(tasks, ui, storage);
                end = c.isEnd();
            } catch (UnsupportedOperationException e) {
                ui.showError("UnknownCommand");
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public String getResponse(String input) {
        String out;
        try {
            Command c = Parser.parse(input, ui);
            out = c.execute(tasks, ui, storage);
        } catch (UnsupportedOperationException e) {
            out = ui.showError("UnknownCommand");
        } catch (DukeException e) {
            out = ui.showError(e.getMessage());
        }
        return out;
    }
}




