package src.main.java.duke;

import src.main.java.duke.command.Command;

/**
 * Duke class is the main class of the program.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of Duke that takes in a file path specifying the location of the
     * storage file.
     * 
     * @param filePath file path specifying the location of the storage file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * run method initiates the program and outlines the operation backbone of the
     * program.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * main method of Duke is called when code is run.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Duke("./src/main/java/duke/data/duke.txt").run();
    }
}
