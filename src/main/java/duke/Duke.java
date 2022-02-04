package duke;

import duke.command.Command;
import duke.task.TaskList;

/**
 * Duke is the main class that houses a 'run' command to call on other classes to process the user input.
 * It helps the user to record down tasks such as Events, Deadlines, and Todos.
 * It can also save the user's tasks into a text file, and load it up when the user interacts with Ducky again.
 *
 * @author Pun Hui Min
 */
public class Duke {
    private Storage storage;
    private String filename;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor method for Duke.
     *
     * @param filepath text file name. Typically called 'duke.txt'.
     */
    public Duke(String filepath) {
        filename = filepath;
        storage = new Storage(filepath);
        ui = new Ui();
        try {
            tasks = storage.readFile(filepath);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Processes the string inputted by the user. Filters the duke.command and calls on other functions to print a
     * string.
     *
     * @throws DukeException when the specified ID number is not in the list, if the time is not provided accurately,
     *                       or if there was no description or command provided.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // show the divider line ("_______")
                ui.showLine();
                Command c = new Parser(fullCommand).parse();
                c.execute(tasks, ui, storage);
                storage.saveFile(tasks.formatTasks());
                isExit = c.isExit();
            } catch (DukeException e) {
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
            Command c = new Parser(input).parse();
            String response = c.execute(tasks, ui, storage);
            storage.saveFile(tasks.formatTasks());
            return response;
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }
}

