package duke;

import java.util.Objects;
import java.util.Timer;

import duke.command.Command;
import duke.gui.DialogueBox;
import duke.logic.DukeException;
import duke.logic.ExitDukeTimerTask;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.TaskStack;
import duke.logic.Ui;
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
import javafx.stage.Stage;

/**
 * Encapsulates the main Duke programme.
 */
public class Duke extends Application {
    /**
     * Layout to contain GUI components.
     */
    private AnchorPane mainLayout;

    /**
     * Scroll pane to display messages between user and Duke GUI.
     */
    private ScrollPane scrollPane;

    /**
     * Container for storing messages between user and Duke GUI.
     */
    private VBox dialogContainer;

    /**
     * Text box for Duke GUI user input.
     */
    private TextField userInput;

    /**
     * Button to send message from user to Duke GUI.
     */
    private Button sendButton;

    /**
     * Overarching container Duke GUI main layout.
     */
    private Scene scene;

    /**
     * Default user profile picture.
     */
    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/default.png")));

    /**
     * Default Duke GUI profile picture.
     */
    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/monke.png")));

    /**
     * Storage responsible for reading from and writing to local file.
     */
    private Storage storage;

    /**
     * List of tasks stored at run-time.
     */
    private TaskList taskList;

    /**
     * UI responsible for displaying messages to user.
     */
    private Ui ui;

    /**
     * Stack of tasks for use with undo command.
     */
    private TaskStack taskStack;

    /**
     * Default constructor for an instance of Duke. Used for GUI.
     */
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage("./data/saved.txt");
            this.taskList = storage.readFromFile();
        } catch (DukeException e) {
            ui.showError(e);
            this.taskList = new TaskList();
        }
        this.taskStack = new TaskStack();
    }

    /**
     * Constructor for an instance of Duke.
     *
     * @param filePath Path at which local file is stored.
     */
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.taskList = storage.readFromFile();
        } catch (DukeException e) {
            ui.showError(e);
            this.taskList = new TaskList();
        }
        this.taskStack = new TaskStack();
    }

    /**
     * Boots up instance of Duke programme.
     */
    public void run() {
        this.ui.openScanner();
        this.ui.showIntro();
        boolean hasExited = false;

        do {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(this.taskList, this.ui, this.storage, this.taskStack);
                hasExited = command.isExitCommand();
            } catch (DukeException e) {
                ui.showError(e);
            }
        } while (!hasExited);

        assert hasExited : "Not supposed to have exited.";

        ui.closeScanner();
    }

    /**
     * Constructs the components for Duke GUI.
     */
    private void initialiseComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
    }

    /**
     * Sets up the title and dimensions of the stage component of Duke GUI.
     *
     * @param stage Stage that is to be set up.
     */
    private void setupStage(Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(400.0);
        stage.setMinWidth(600.0);
    }

    /**
     * Sets up the dimensions of the main layout component of Duke GUI.
     */
    private void setupMainLayout() {
        mainLayout.setPrefSize(600.0, 400.0);
    }

    /**
     * Sets up the dimensions and scroll behaviour of the scroll pane component of Duke GUI.
     */
    private void setupScrollPane() {
        scrollPane.setPrefSize(600.0, 365.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Sets up the dimensions and initial message of the dialogue container component of Duke GUI.
     */
    private void setupDialogueContainer() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().add(
                new DialogueBox(new Label(ui.showIntro()), new ImageView(duke), false));
    }

    /**
     * Sets up the dimensions and handler of the user input component of Duke GUI.
     */
    private void setupUserInput() {
        userInput.setPrefWidth(535.0);
        userInput.setPrefHeight(25.0);
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Sets up the dimensions and handler of the send button component of Duke GUI.
     */
    private void setupSendButton() {
        sendButton.setPrefWidth(55.0);
        sendButton.setPrefHeight(25.0);
        sendButton.setOnMouseClicked((event) -> handleUserInput());
    }

    /**
     * Sets up the positions of all other components of Duke GUI with respect to the anchor pane
     * component.
     */
    private void setupAnchorPane() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Collectively sets up all Duke GUI components.
     *
     * @param stage Stage that is to be set up.
     */
    private void setupComponents(Stage stage) {
        setupStage(stage);
        setupMainLayout();
        setupScrollPane();
        setupDialogueContainer();
        setupUserInput();
        setupSendButton();
        setupAnchorPane();
    }

    /**
     * The gateway method for initialisation, set up, and display of Duke GUI components.
     *
     * @param stage Stage that is to be set up and displayed.
     */
    @Override
    public void start(Stage stage) {
        initialiseComponents();
        setupComponents(stage);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handler for user input component of Duke GUI. Receives user input and appends dialogue
     * container component with both user input and computed Duke output.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogueBox(userText, new ImageView(user), true),
                new DialogueBox(dukeText, new ImageView(duke), false)
        );
        userInput.clear();
    }

    /**
     * Computes string output from Duke with input command.
     *
     * @param fullCommand Command that is to be passed to Duke.
     * @return String output from Duke.
     */
    private String getResponse(String fullCommand) {
        try {
            Timer timer = new Timer();
            Command command = Parser.parse(fullCommand);
            if (command.isExitCommand()) {
                timer.schedule(new ExitDukeTimerTask(), 1000);
            }
            return command.execute(taskList, ui, storage, taskStack);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    public static void main(String[] args) {
        new Duke("./data/saved.txt").run();
    }
}
