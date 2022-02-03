package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * This class consists of the main method, and to initialise the program.
 */
public class Duke extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;


    /**
     * This is the constructor to create a new instance of Duke.
     * @param filePath is the directory for the file location on the computer.
     * @throws IOException if the file is corrupt or file is not found.
     * @throws ParseException if the contents of the file is not in the correct format.
     */
    public Duke (String filePath) throws IOException, ParseException {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //Scroll down to the end every time dialogContainer's height changes.
        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

    }

    /**
     * Starts the program Duke for the user
     * @throws IOException if the file is corrupt or file is not found.
     * @throws ParseException if the contents of the file is not in the correct format.
     */
    public void run() throws IOException, ParseException {

        Parser parser = new Parser();

        while (true) {
            try {
                Command command = parser.readCommand();
                command.runCommand(taskList, ui, storage);
            } catch (DukeException e) {
                ui.showWrongCommand();
            }
        }
    }

/*    public static void main(String[] args) throws IOException, ParseException {
        //new Duke("data/tasks.txt").run();
    }*/



    static void runHelpCommand() {
        System.out.println("    Hello. You can run a few commands with this machine.");
        System.out.println("    1. Type todo to create a task at hand. (eg. todo homework today)");
        System.out.println("    2. Type event to create an event. (eg. event Career Fair /at 26/01/2022 10:00 AM)");
        System.out.println("    3. Type deadline to create an deadline. "
                + "(eg. deadline CS2103 Assignement /by 29/01/2022 11:59 PM)");
        System.out.println("    4. Type list to see what are the tasks on hand.");

    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException, IOException, ParseException {

        try {
            Parser parser = new Parser();
            Command c = parser.processCommand(input);
            String returnMessage = c.runCommand(taskList, ui, storage);
            return returnMessage;
        } catch (DukeException e) {
            return "Error, please try again";
        }

    }
}
