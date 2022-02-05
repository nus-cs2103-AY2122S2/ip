package duke;

import duke.command.Command;
import duke.command.Parser;
import duke.task.Storage;
import duke.task.TaskList;
import duke.gui.Ui;

/**
 * Represents a Duke chatbot.
 * A Duke chatbot will save or load the user's tasks in a local file.
 */

public class Duke {

    private TaskList taskList;
    private String userName;
    private String taskFilePath;
    private Ui ui;
    private Storage storage;
    private String dukeResponse;

    /**
     * Constructor to initialize Duke & its various components.
     *
     * @param filePath Path of savefile of tasks information
     * @return Nothing.
     */
    public Duke(String filePath) {
        taskFilePath = filePath;
        ui = new Ui();
        storage = new Storage(taskFilePath, ui);
        // load TaskList from existing data
        taskList = new TaskList(storage.loadFileContents());
    }

    /**
     * The main method which intializes Duke & runs it.
     *
     * @param args Unused.
     *             return Nothing.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }


    /**
     * Main logic of Duke to set it up & run it.
     */
    public void run() {

        // Jarvis introduces himself, asks for user's name & greets user
        userName = ui.showWelcome();

        // initialize the chat session as active
        boolean active = true;

        while (active) {
            try {
                String userInput = ui.readCommand();
                Command c = Parser.parse(userInput);
                String output = c.execute(taskList, ui, storage);
                System.out.println("-> " + output);
                active = c.isActive();
            } catch (DukeException e) {
                String out = ui.showError(e.getMessage());
                System.out.println("-> " + out);
            }
        }

    }

    /**
     * Reads user's input & executes corresponding commands.
     * @return
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            dukeResponse = c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            dukeResponse = e.getMessage();
        }
        return dukeResponse;
    }

    public String welcomeUser(String userInput) {
        return "welcome Sam!";
    }

}
