package duke;

import java.io.IOException;
import java.util.Objects;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Duke runs a chatbot that saves and updates tasks input by the user.
 *
 * @author Chan Yi Juan
 * @version 0.1
 * @since 2022-01-10
 */
public class Duke extends Application {
    /**
     * Encapsulates Duke's UI interaction.
     */
    private final Ui ui;
    /**
     * Encapsulates a list of tasks.
     */
    private final TaskList<Task> tasks;
    /**
     * Encapsulates Duke's storage logic
     */
    private final Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Instantiates a new Duke.
     *
     * @throws IOException the io exception
     */
    public Duke() throws IOException {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = this.storage.loadSavedTasks();
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) {

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

        // more code to be added here later
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

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        String userText = ui.readUserInput();
        String dukeText = getResponse(userInput.getText());
        if (!userText.equals("bye")) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText, user),
                    DialogBox.getDukeDialog(dukeText, duke)
            );
        } else {
            System.exit(0);
        }
        userInput.clear();
    }

    public void welcomeUser() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(ui.showWelcomeMessage(), duke)
        );
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            String dukeOutput = cmd.execute(tasks, ui, storage);
            return dukeOutput;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
    /**
     * Runs the Duke chatbot program.
     */
    public void run() {
        ui.showWelcomeMessage();
        boolean exitDuke = false;

        while (!exitDuke) {
            try {
                String fullCommand = ui.readUserInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                exitDuke = c.isExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            } finally {
            }
        }
    }

    /**
     * The entry point of the Duke application.
     *
     * @param args the input arguments
     * @throws IOException when storage file does not exist
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }
}
