package myboss;

import java.time.format.DateTimeParseException;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

/**
 * Represents the Personal Assistant Chatbot that helps a person to keep track of various things.
 * A MyBoss Object corresponds to a Personal Assistant Chatbot.
 */
public class MyBoss extends Application {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Creates a MyBoss Object with the specified file path for saving tasks.
     *
     * @param filePath path to file where tasks will be saved.
     */
    public MyBoss(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskListFromFile());
    }

    public MyBoss() {
        String filePath = "./data/tasks.txt";
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTaskListFromFile());
    }

    /**
     * Logic of the Personal Assistant Chatbot that receives input and acts accordingly.
     */
    public void run() {
        ui.outputGreeting();

        while (true) {
            String userCmd = ui.getUserCmd();
            String[] userCmdSplit = Parser.splitUserCmd(userCmd);
            String command = userCmdSplit[0];

            String remainingUserInput = Parser.getRemainingUserCmd(userCmdSplit);

            try {
                switch (command) {
                case "bye":
                    storage.updateFile(tasks.getTaskList());
                    ui.outputFarewell();
                    System.exit(0);
                case "list":
                    ui.outputTaskList(tasks.getTaskList());
                    break;
                case "mark":
                    //fallthrough
                case "unmark":
                    // fallthrough
                case "delete":
                    int currTaskIndex = Parser.getTaskIndex(userCmdSplit);
                    Task currTask = tasks.get(currTaskIndex);
                    if (command.equals("mark")) {
                        ui.outputMarked(currTask);
                    } else if (command.equals("unmark")) {
                        ui.outputUnmarked(currTask);
                    } else {
                        ui.outputDeleteTask(tasks.deleteTask(currTaskIndex));
                    }
                    break;
                case "todo":
                    ToDo newToDo = new ToDo(remainingUserInput);
                    tasks.addTask(newToDo);
                    ui.addTaskOutput(newToDo);
                    break;
                case "deadline":
                    String[] parsedDeadline = Parser.parseDeadlineUserCmd(remainingUserInput);
                    String deadlineName = parsedDeadline[0];
                    String timeBy = parsedDeadline[1];
                    Deadline newDeadline = new Deadline(deadlineName, timeBy);
                    tasks.addTask(newDeadline);
                    ui.addTaskOutput(newDeadline);
                    break;
                case "event":
                    String[] parsedEvent = Parser.parseEventUserCmd(remainingUserInput);
                    String eventName = parsedEvent[0];
                    String timeRange = parsedEvent[1];
                    Event newEvent = new Event(eventName, timeRange);
                    tasks.addTask(newEvent);
                    ui.addTaskOutput(newEvent);
                    break;
                case "find":
                    ui.outputFoundTasks(tasks.findTasks(remainingUserInput));
                    break;
                default:
                    throw new MyBossException(" OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
            } catch (StringIndexOutOfBoundsException ex) {
                Ui.outputMyBoss(" OOPS!!! Argument after missing /at or /by!!!");
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.outputMyBoss(" OOPS!!! I think you're missing some arguments!");
            } catch (MyBossException e) {
                Ui.outputMyBoss(e.getMessage());
            } catch (DateTimeParseException e) {
                Ui.outputMyBoss("Date must be of format yyyy-mm-dd!");
            }
        }
    }

    public static void main(String[] args) {
        new MyBoss("./data/tasks.txt").run();
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
        stage.setTitle("MyBoss");
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

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
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
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}

