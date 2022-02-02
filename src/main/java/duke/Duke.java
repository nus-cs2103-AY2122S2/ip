package duke;

import java.io.IOException;
import java.util.Scanner;

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
 * Duke is a task tracker interactive chatbot.
 *
 * @author Chen Yu An
 * @version v0.1
 */
public class Duke extends Application {
    private final Scanner scanner = new Scanner(System.in);
    private boolean endProgram = false; // state to terminate the program

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    //JavaFX fields
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

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

    public static void main(String[] args) {
        new Duke().startProgram();
    }

    private void startProgram() {
        storage.makeFile();
        storage.loadFile(taskList);
        ui.welcomeMessage();

        while (!endProgram) {
            try {
                String input = scanner.nextLine(); // user input

                // handle user input to command, description and time of task if applicable
                UserInput userInput = parser.parseInput(input);
                String command = userInput.getCommand();

                // exit program when user input "bye"
                if (command.equals("bye")) {
                    ui.endProgram();
                    endProgram = true;
                    break;
                }

                // list out the tasks
                if (command.equals("list")) {
                    taskList.listTask(userInput);
                    continue;
                }

                // mark certain task as done
                if (command.startsWith("mark")) {
                    taskList.markDone(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // mark certain task as not done yet
                if (command.startsWith("unmark")) {
                    taskList.markUndone(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // delete task
                if (command.startsWith("delete")) {
                    taskList.deleteTask(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // create ToDoTask
                if (command.equals("todo")) {
                    taskList.addTask(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // create DeadlineTask
                if (command.equals("deadline")) {
                    taskList.addTask(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // create EventTask
                if (command.equals("event")) {
                    taskList.addTask(userInput);
                    storage.writeToFile(taskList.getList());
                    continue;
                }

                // find from list
                if (command.equals("find")) {
                    taskList.findTask(userInput);
                    continue;
                }

                // Invalid command inputs result
                throw new DukeCommandDoesNotExistException("OOPS!!! This command does not exist.");

            } catch (DukeException e) {
                ui.errorMessage(e);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        }

        // close the scanner
        scanner.close();
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws InterruptedException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );

        if (userInput.getText().equals("bye")) {
            Platform.exit();
            System.exit(0);
        }

        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        try {
            UserInput userInput = parser.parseInput(input);
            String command = userInput.getCommand();

            // exit program when user input "bye"
            if (command.equals("bye")) {
                String returnMessage = ui.endProgramFX();
                endProgram = true;
                return returnMessage;
            }

            // list out the tasks
            if (command.equals("list")) {
                String returnMessage = taskList.listTaskFX(userInput);
                return returnMessage;
            }

            // mark certain task as done
            if (command.startsWith("mark")) {
                String returnMessage = taskList.markDoneFX(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // mark certain task as not done yet
            if (command.startsWith("unmark")) {
                String returnMessage = taskList.markUndoneFX(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // delete task
            if (command.startsWith("delete")) {
                String returnMessage = taskList.deleteTaskFX(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // create ToDoTask
            if (command.equals("todo")) {
                String returnMessage = taskList.addTaskFX(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // create DeadlineTask
            if (command.equals("deadline")) {
                String returnMessage = taskList.addTaskFX(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // create EventTask
            if (command.equals("event")) {
                String returnMessage = taskList.addTaskFX(userInput);
                storage.writeToFile(taskList.getList());
                return returnMessage;
            }

            // find from list
            if (command.equals("find")) {
                String returnMessage = taskList.findTaskFX(userInput);
                return returnMessage;
            }

            // Invalid command inputs result
            throw new DukeCommandDoesNotExistException("OOPS!!! This command does not exist.");

        } catch (DukeException | IOException e) {
            return e.getMessage();

        }
    }
}
