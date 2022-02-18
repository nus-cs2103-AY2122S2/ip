package duke;

import duke.command.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ContactList contacts;

    /**
     * Initializes task list and reads tasks from storage.
     *
     * @param filePath File path to read task data from.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        contacts = new ContactList();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
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
                c.execute(tasks, ui, storage, contacts);
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
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "duke", "data.txt");
        boolean fileExists = java.nio.file.Files.exists(path);
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     * @param input User input.
     * @return Duke's response to user.
     */
    public String getResponse(String input) {
        String response;
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage, contacts);
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }

}
