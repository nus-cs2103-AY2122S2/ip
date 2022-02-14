package duke;

import java.io.IOException;

import javafx.scene.image.Image;

import duke.helpers.Parser;
import duke.helpers.Storage;
import duke.helpers.TaskList;
import duke.helpers.Ui;

/**
 * Represents a chatbot. A Duke object corresponds to
 * a chatbot that tracks tasks for users
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duck.png"));

    /**
     * Constructs a Duke object where Task data is stored in a given file with given directory.
     *
     * @param dirPath Directory path to store Task data.
     * @param filePath File path to store Task data.
     */
    public Duke(String dirPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(dirPath, filePath);
        try {
            this.tasks = new TaskList(this.storage.loadFileContents());
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public Duke() {
        String dirPath = "./src/main/data";
        String filePath = "./src/main/data/duke.txt";
        this.ui = new Ui();
        this.storage = new Storage(dirPath, filePath);
        try {
            this.tasks = new TaskList(this.storage.loadFileContents());
        } catch (IOException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke chatbot, saving a list of tasks in the specified file.
     */
    public void run() {
        ui.welcome();
        String userInput = "";

        while (!userInput.equals("bye")) {
            userInput = ui.readCommand();
            assert(!userInput.equals(""));
            Parser.parse(userInput);
        }
        ui.closeScanner();
    }

    /**
     * Creates a Duke object and calls the run method.
     */
    public static void main(String[] args) throws IOException {
        String dirPath = "./src/main/data";
        String filePath = "./src/main/data/duke.txt";
        Duke duke = new Duke(dirPath, filePath);
        duke.run();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
            return Parser.parse(input);
    }

}
