package jeff.main;

import jeff.command.Command;

import jeff.parser.Parser;

import jeff.storage.Storage;

import jeff.task.TaskList;

import jeff.ui.Ui;

/**
 * Jeff class is a task manager with a variety of commands.
 * To see the full list of commands, check the readme.txt.
 */
public class Jeff {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Jeff class.
     *
     * @param filePath Path of saved file.
     */
    public Jeff(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JeffException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JeffException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Jeff("data/tasks.txt").run();
    }
}
