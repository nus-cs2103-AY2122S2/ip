package duke;

import java.io.IOException;

/**
 * Represents a Personal Assistant Chatbot based on Project duke.Duke.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Duke {
    /**
     * Contains a list of tasks stored for the user
     */
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructor for the Chatbot.
     *
     * @param filepath the file path for the text file containing tasks to be stored
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            ui.showMessage("File cannot be created. Check directory.");
            System.exit(0);
        }
    }

    /**
     * The main method. Creates a Duke instance and runs it.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    /**
     * Runs the ChatBot program
     */
    public void run() {
        ui.showWelcomeMessage();
        ui.userInput(tasks, storage);
        ui.showFarewellMessage();
    }
}
