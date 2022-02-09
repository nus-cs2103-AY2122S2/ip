package duke;

import duke.command.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes task list and reads tasks from storage.
     *
     * @param filePath File path to read task data from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Duke() {
        this("../data/duke.txt");
    }

    /**
     * Runs the application.
     */
    public void run() {
        ui.showWelcome();
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
     * Creates and runs Duke instance with a specified file path.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * @param input User input.
     * @return Duke's response to user.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
