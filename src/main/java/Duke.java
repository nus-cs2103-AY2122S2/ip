import java.io.IOException;

/**
 * Represents a Personal Assistant Chatbot based on Project Duke.
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

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        ui.showWelcomeMessage();
        ui.userInput(tasks, storage);
        ui.showFarewellMessage();
    }
}
