package duke;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import duke.command.Command;
import duke.gui.DialogueBox;
import duke.logic.DukeException;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.Ui;
import javafx.application.Application;
import javafx.application.Platform;
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
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(
            "/images/default.png")));
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
     * Default constructor for an instance of Duke. Used for GUI.
     */
    public Duke() {
        try {
            this.ui = new Ui();
            this.storage = new Storage("./data/saved.txt");
            this.taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.showError(e);
            this.taskList = new TaskList();
        }
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
            this.taskList = new TaskList(storage.readFromFile());
        } catch (DukeException e) {
            ui.showError(e);
            this.taskList = new TaskList();
        }
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
                command.execute(this.taskList, this.ui, this.storage);
                hasExited = command.isExitCommand();
            } catch (DukeException e) {
                ui.showError(e);
            }
        } while (!hasExited);

        ui.closeScanner();
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

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

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        dialogContainer.getChildren().add(
                new DialogueBox(new Label(ui.showIntro()), new ImageView(duke)));

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        stage.setScene(scene);
        stage.show();
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogueBox(userText, new ImageView(user)),
                new DialogueBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String fullCommand) {
        try {
            Timer timer = new Timer();
            Command command = Parser.parse(fullCommand);
            if (command.isExitCommand()) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                        System.exit(0);
                    }
                }, 2 * 1000);
            }
            return command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

    public static void main(String[] args) {
        new Duke("./data/saved.txt").run();
    }
}
