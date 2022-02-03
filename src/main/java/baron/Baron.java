package baron;

import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import baron.commands.Command;
import baron.commands.CommandManager;
import baron.exceptions.BaronException;
import baron.tasks.TaskManager;
import baron.util.Storage;
import baron.util.TextUi;

/**
 * Main class for the Baron application that user uses to run. The Baron application keeps and
 * tracks tasks like a to-do list.
 */
public class Baron extends Application {
    private static final String DEFAULT_STORAGE_FILE_PATH = "data/baron.txt";
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
            command = commandManager.parseCommand(fullCommand);
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
        stage.show();
    }
}
