package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Represents the Duke application
 */
public class Duke extends Application {
    private TaskManager taskManager;
    private Ui ui;
    private Storage storage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    Duke(String filePath) {
        ui = new Ui();
        taskManager = new TaskManager();
        try {
            storage = new Storage(filePath);
            taskManager = new TaskManager(storage.load());
        } catch (DukeException e) {
            ui.print(e.toString());
            taskManager = new TaskManager();
        }
    }

    public String getResponse(String input) {
        if (Parser.isExit(input)) {
            try {
                storage.save(taskManager.getTasks());
                return Parser.parseCommand(input, taskManager);
            } catch (DukeInvalidFileSaveException e) {
                return e.toString();
            }
        }
        return Parser.parseCommand(input, taskManager);
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

        stage.setScene(scene);
        stage.show();

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

    }

    /**
     * Runs the Duke application
     */
    public void run() {
        ui.greet();
        while (true) {
            String userInput = ui.readCommand();
            String response = Parser.parseCommand(userInput, taskManager);
            ui.print(response);
            if (Parser.isExit(userInput)) {
                try {
                    storage.save(taskManager.getTasks());
                } catch (DukeInvalidFileSaveException e) {
                    ui.print(e.toString());
                }
                return;
            }
        }
    }

    /**
     * Main driver function of the Duke application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
