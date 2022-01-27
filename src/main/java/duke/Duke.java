package main.java.duke;

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
                ui.horizontal(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand, ui);
                c.execute(tasks, ui, storage);
                end = c.isEnd();
            } catch (UnsupportedOperationException e) {
                ui.showError("UnknownCommand");
            } finally {
                ui.horizontal();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
