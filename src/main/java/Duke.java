import java.io.FileNotFoundException;

import duke.commands.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * A task tracker bot named Duke aka mum.
 * Stores list of tasks performed by the user.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private String filePath = "./data/duke.txt";
    private ScrollPane scrollPane;
    private GridPane gridPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * A constructor that initialize this Duke class.
     * Loads saved tasks from a given file.
     *
     * @param filePath to load data from into this bot
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Constructor that initializes this Duke Class.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadTasksFromFile());
        } catch (FileNotFoundException e) {
            Ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Starts Duke and await for input by user.
     */
    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.isEmpty()) {
                    break;
                }
                Command<String> c = Parser.parseInput(fullCommand, tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.err.print(Ui.getLineDivider() + e + Ui.getLineDivider());
            }
        }
        Ui.showGoodBye();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    public String handleUserInput(String fullCommand) {
        boolean isExit = false;
        String response = "";
        try {
            Command<String> c = Parser.parseInput(fullCommand, tasks, storage);
            if (c.isExit()) {
                return "Bye. Click on the close button to put me to sleep.";
            }
            response = c.execute();
        } catch (DukeException e) {
            return e.getMessage();
        }
        return response;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    /**
     * Method that initialises this Duke class and run() the bot.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
