package duke;

import java.nio.file.Path;

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
    public Duke(Path directory, Path filePath) {
        ui = new Ui();
        storage = new Storage(directory, filePath);
        contacts = new ContactList();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
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
     * Gets Duke's response to user.
     *
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
