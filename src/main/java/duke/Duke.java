package duke;

import java.io.IOException;

import duke.exception.DukeCommandDoesNotExistException;
import duke.exception.DukeException;
import duke.io.Parser;
import duke.io.UserInput;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
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
 * Duke is a task tracker interactive chat-bot.
 *
 * @author Chen Yu An
 * @version v0.2
 */
public class Duke extends Application {
    private boolean isRunning = true; // state to terminate the program

    private Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    //JavaFX fields
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/rabbit.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/alice.png"));

    /**
     * Duke constructor.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.parser = new Parser();

        try {
            this.storage = new Storage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        // Make a txt file to store data and load stored task from data if there is any
        storage.makeFile();
        storage.loadFile(taskList);

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
        stage.setTitle("Dream");
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Welcome message
        Label dukeText = new Label(ui.welcomeMessage());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        // Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
            if (!isRunning) {
                Platform.exit();
                System.exit(0);
            }
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
            if (!isRunning) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        userInput.clear();
    }

    /**
     * Process the response based on the user's input.
     *
     * @param input User's input.
     * @return Response in String.
     */
    private String getResponse(String input) {
        try {
            UserInput userInput = parser.parseInput(input);
            String command = userInput.getCommand();

            // exit program when user input "bye"
            if (command.equals("bye")) {
                String returnMessage = ui.endProgramFX();
                isRunning = false;
                return returnMessage;
            }

            // list out the tasks
            if (command.equals("list")) {
                return taskList.listTask(userInput);
            }

            // mark certain task as done
            if (command.startsWith("mark")) {
                String returnMessage = taskList.markDone(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // mark certain task as not done yet
            if (command.startsWith("unmark")) {
                String returnMessage = taskList.markUndone(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // delete task
            if (command.startsWith("delete")) {
                String returnMessage = taskList.deleteTask(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // create ToDoTask
            if (command.equals("todo")) {
                String returnMessage = taskList.addTask(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // create DeadlineTask
            if (command.equals("deadline")) {
                String returnMessage = taskList.addTask(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // create EventTask
            if (command.equals("event")) {
                String returnMessage = taskList.addTask(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // find from list
            if (command.equals("find")) {
                return taskList.findTask(userInput);
            }

            // show the list of commands
            if (command.equals("help")) {
                return taskList.listOfCommands();
            }

            // Invalid command inputs result
            throw new DukeCommandDoesNotExistException("OOPS!!! This command does not exist.");

        } catch (DukeException | IOException e) {
            return e.getMessage();
        }
    }
}
