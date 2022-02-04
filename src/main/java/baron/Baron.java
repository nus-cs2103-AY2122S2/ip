package baron;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import baron.commands.Command;
import baron.commands.CommandManager;
import baron.exceptions.BaronException;
import baron.tasks.TaskManager;
import baron.ui.DialogBox;
import baron.util.Storage;
import baron.util.TextUi;

/**
 * Main class for the Baron application that user uses to run. The Baron application keeps and
 * tracks tasks like a to-do list.
 */
public class Baron extends Application {
    private static final String DEFAULT_STORAGE_FILE_PATH = "data/baron.txt";

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image baron = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private final Scanner inputScanner;
    private final TaskManager taskManager;
    private final CommandManager commandManager;
    private final Storage storage;
    private final TextUi textUi;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public Baron() {
        this(Baron.DEFAULT_STORAGE_FILE_PATH);
    }

    /**
     * Constructs a {@code Baron} object with the specified relative file path.
     *
     * @param relativeFilePath the relative file path to be used for Storage.
     */
    public Baron(String relativeFilePath) {
        TaskManager taskManagerTemp;
        this.inputScanner = new Scanner(System.in);
        this.storage = new Storage(relativeFilePath);
        try {
            taskManagerTemp = new TaskManager(this.storage.load());
        } catch (BaronException e) {
            TextUi.printCommandOutput(e.toString());
            taskManagerTemp = new TaskManager();
        }
        this.taskManager = taskManagerTemp;
        this.commandManager = new CommandManager(this.taskManager, this.storage);
        this.textUi = new TextUi(this.taskManager);
    }

    /**
     * Starts the Baron application.
     */
    private void start() {
        this.textUi.showWelcomeMessage();
        Command command;

        do {
            String fullCommand = inputScanner.nextLine();
            command = this.commandManager.parseCommand(fullCommand);
            TextUi.printCommandOutput(command.execute());
        }
        while (!command.isByeCommand());

        try {
            this.storage.save(this.taskManager.getAllTasks());
        } catch (BaronException e) {
            TextUi.printCommandOutput(e.toString());
        }
    }

    /**
     * Initialises and starts the Baron application.
     *
     * @param args the command line arguments (not used).
     */
    public static void main(String[] args) {
        new Baron("data/baron.txt").start();
    }

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

        // Set Window configurations
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        stage.show();
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
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getBaronDialog(dukeText, new ImageView(baron))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return this.commandManager.parseCommand(input).execute();
    }
}
