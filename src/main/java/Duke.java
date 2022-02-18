import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import myPackage.Parser;
import myPackage.Storage;
import myPackage.TaskList;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.scene.image.Image;

/**
 * The main class for Dukebot. In charge of setting up saved files.
 */

public class Duke {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke(String filePath) throws IOException {
        TaskList.list = new ArrayList<>();
        Storage.checkFile();
        Storage.load(filePath);
        assert TaskList.list != null;
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Gets a response from the chatbot.
     * @param input input from user to chatbot.
     * @return response from chatbot.
     */
    public String getResponse(String input) {
        return Parser.parseCommand(input);
    }

}
