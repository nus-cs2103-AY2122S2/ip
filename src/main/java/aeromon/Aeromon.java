package aeromon;

import java.util.ArrayList;

import aeromon.command.Command;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * Aeromon class that runs the Aeromon bot.
 */
public class Aeromon {

    private TaskArrayList taskList;
    private final Ui ui;
    private final Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    private Image aeromon = new Image(this.getClass().getResourceAsStream("/images/Aeromon.jpg"));

    /**
     * Constructs the Aeromon object.
     */
    public Aeromon() {
        ui = new Ui();
        storage = new Storage("data/localTasks.txt");

        try {
            taskList = new TaskArrayList(storage.getFile());
        } catch (AeromonException e) {
            ui.print(e.getMessage());
            taskList = new TaskArrayList(new ArrayList<>());
        }
    }

    /**
     * Starts the Aeromon bot by greeting the user with the greeting message.
     * @return the greeting message in String.
     */
    public String start() {
        return "Hey, Aeromon here! Fancy a cup of tea?";
    }

    /**
     * Gets the response from the CommandManager.
     * @param input User input.
     * @return The response String.
     */
    public String getResponse(String input) {
        assert input != null : "Input is null";

        try {
            Command command = CommandManager.read(input);
            return command.execute(taskList, ui, storage);
        } catch (AeromonException exception) {
            return exception.getMessage();
        }
    }
}
