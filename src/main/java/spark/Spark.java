package spark;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import spark.commands.commandtypes.Command;
import spark.commands.commandtypes.ListCommand;
import spark.exceptions.SparkException;
import spark.exceptions.fileexceptions.FileException;
import spark.exceptions.fileexceptions.TaskDecodingException;
import spark.storage.Storage;
import spark.tasks.TaskList;

public class Spark extends Application {
    private static final String defaultFilePathString = "spark_save_file.txt";
    private ScrollPane scrollPane;
    private VBox tasksContainer;
    private TextField userInput;
    private Button runButton;
    private Scene scene;
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /** Starts an instance of Spark that stores saved Tasks in
     * the default relative file-path on the user's hard-disk.
     */
    public Spark() {
        this.ui = new Ui();

        try {
            this.storage = new Storage(defaultFilePathString);
            this.taskList = new TaskList(storage.readTasksFile());
        } catch (FileException | TaskDecodingException e) {
            ui.printException(e);
            ui.printMessageWithDivider("Note that any changes will not be saved to your save-file.");
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts an instance of Spark that stores saved Tasks in
     * the specified relative file-path on the user's hard-disk.
     */
    public Spark(String filePathString) {
        this.ui = new Ui();

        try {
            this.storage = new Storage(filePathString);
            this.taskList = new TaskList(storage.readTasksFile());
        } catch (FileException | TaskDecodingException e) {
            ui.printException(e);
            this.taskList = new TaskList();
        }
    }

    public void run() {
        new ListCommand().execute(taskList, ui, storage);
        ui.printWelcomeMessage();

        boolean isExit = false;

        while (!isExit) {
            try {
                String userInput = ui.getInput();
                Command command = Parser.parseInput(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (SparkException e) {
                ui.printException(e);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        /* Step 1: Setting up required components */
        // The container for the content of the chat to scroll
        scrollPane = new ScrollPane();
        tasksContainer = new VBox();
        scrollPane.setContent(tasksContainer);
        // The input box for the user to input commands
        userInput = new TextField();
        runButton = new Button("Run");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, runButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        /* Step 2: Formatting the window to look as expected */
        stage.setTitle("Spark");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        tasksContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        runButton.setPrefWidth(55.0);
        // position list of tasks to the top of window
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        // position user-input to bottom left of window
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        // position run button to bottom-right of window
        AnchorPane.setBottomAnchor(runButton, 1.0);
        AnchorPane.setRightAnchor(runButton, 1.0);
    }

    public static void main(String[] args) {
        new Spark().run();
    }
}
