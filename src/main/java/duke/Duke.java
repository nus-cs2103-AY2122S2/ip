package duke;


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

import duke.task.TaskList;
import duke.task.Event;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Todo;

import java.util.Objects;
import java.util.Scanner;

/**
 * Duke class used for running the Duke chat bot
 */
public class Duke extends Application {

    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private final Parser parser;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Default constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage("save.txt");
        try {
            tasks = new TaskList(storage.ParseFile());
        } catch (DukeException e) {
            ui.sayMessage(e.getErrorMsg());
        }
    }

    /**
     * Default constructor for Duke
     *
     * @param fileName File name to give save data
     */
    public Duke(String fileName) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(fileName);
        try {
            tasks = new TaskList(storage.ParseFile());
        } catch (DukeException e) {
            ui.sayMessage(e.getErrorMsg());
        }
    }

    /**
     * Main function
     *
     * @param args arguments to pass in
     */
    public static void main(String[] args) {
        new Duke("save.txt").run();
    }

    /**
     * Our pseudo main function that basically handles all functionality of duke
     * All input handling and parsing is handled by respective classes
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        boolean isExit = true;
        while (isExit) {
            try {
                String input = scanner.nextLine();
                Parser.RESULT parseResult = parser.parseInput(input);
                switch (parseResult) {
                case BYE:
                    ui.sayGoodbye();
                    storage.OverwriteFile(tasks.getTasks());
                    isExit = false;
                    break;
                case LIST:
                    ui.listTasks(tasks);
                    break;
                case MARK: {
                    int index = parser.parseIndex(input);
                    if (index < tasks.getTasks().size()) {
                        tasks.markTaskDone(index);
                        ui.sayMessage("Nice! I've marked this task as done:\n" + tasks.getTask(index).toString());
                    } else {
                        throw new DukeException("No such task exists.");
                    }
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                }
                case UNMARK: {
                    int index = parser.parseIndex(input);
                    if (index < tasks.getTasks().size()) {
                        tasks.markTaskNotDone(index);
                        ui.sayMessage("Nice! I've marked this task as not done:\n" + tasks.getTask(index).toString());
                    } else {
                        throw new DukeException("No such task exists.");
                    }
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                }
                case DELETE:
                    int index = parser.parseIndex(input);
                    if (index < tasks.getTasks().size()) {
                        Task removedTask = tasks.removeTask(index);
                        ui.sayMessage("Noted. I've removed this task:\n" + removedTask.toString());
                    } else {
                        throw new DukeException("No such task exists.");
                    }
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                case TODO: {
                    Todo newTodo = new Todo(parser.parseTodo(input));
                    tasks.addTask(newTodo);
                    ui.sayMessage(
                            "Got it. I've added this task:\n" + newTodo + "\n" + "Now you have " + tasks.getTasks()
                                    .size() + " tasks in the list.");
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                }
                case DEADLINE:
                    String[] deadlineInput = parser.parseDeadline(input);
                    Deadline newDeadline = new Deadline(deadlineInput[0], deadlineInput[1]);
                    tasks.addTask(newDeadline);
                    ui.sayMessage(
                            "Got it. I've added this task:\n" + newDeadline + "\n" + "Now you have " + tasks.getTasks()
                                    .size() + " tasks in the list.");
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                case EVENT:
                    String[] eventInput = parser.parseEvent(input);
                    Event newEvent = new Event(eventInput[0], eventInput[1]);
                    tasks.addTask(newEvent);
                    ui.sayMessage(
                            "Got it. I've added this task:\n" + newEvent + "\n" + "Now you have " + tasks.getTasks()
                                    .size() + " tasks in the list.");
                    storage.OverwriteFile(tasks.getTasks());
                    break;
                case FIND:
                    String word = parser.parseFind(input);
                    ui.listTasks(tasks.findTasks(word));
                    break;
                case ERROR:
                    throw new DukeException("Sorry :( I don't know what this means.");
                }
            } catch (DukeException e) {
                ui.sayMessage(e.getErrorMsg());
            }
        }
    }


    /**
     * Overloaded start function inherited from Application
     *
     * @param stage
     */
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        stage.setScene(scene);
        stage.show();

        dukeSayMessage("Hello! I am Duke :)");

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // To make the chat box scroll down everytime it is updated
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container
     *
     * @param text String containing text to add
     * @return a label with the specified text that had word wrap enabled
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
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

    private void dukeSayMessage(String text) {
        Label dukeText = new Label(text);
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeText, new ImageView(duke)));
    }

    private String getResponse(String input) {
        String response = "What?";
        try {
            Parser.RESULT parseResult = parser.parseInput(input);
            switch (parseResult) {
            case BYE:
                //ui.sayGoodbye();
                storage.OverwriteFile(tasks.getTasks());
                response = "Goodbye";
                break;
            case LIST:
                //ui.listTasks(tasks);
                response = ui.listTasksAsString(tasks);
                break;
            case MARK: {
                int index = parser.parseIndex(input);
                if (index < tasks.getTasks().size()) {
                    tasks.markTaskDone(index);
                    response = "Nice! I've marked this task as done:\n" + tasks.getTask(index).toString();
                } else {
                    throw new DukeException("No such task exists.");
                }
                storage.OverwriteFile(tasks.getTasks());
                break;
            }
            case UNMARK: {
                int index = parser.parseIndex(input);
                if (index < tasks.getTasks().size()) {
                    tasks.markTaskNotDone(index);
                    response = "Nice! I've marked this task as not done:\n" + tasks.getTask(index).toString();
                } else {
                    throw new DukeException("No such task exists.");
                }
                storage.OverwriteFile(tasks.getTasks());
                break;
            }
            case DELETE:
                int index = parser.parseIndex(input);
                if (index < tasks.getTasks().size()) {
                    Task removedTask = tasks.removeTask(index);
                    response = "Nice! I've removed this task:\n" + removedTask.toString();
                } else {
                    throw new DukeException("No such task exists.");
                }
                storage.OverwriteFile(tasks.getTasks());
                break;
            case TODO: {
                Todo newTodo = new Todo(parser.parseTodo(input));
                tasks.addTask(newTodo);
                response = "Got it. I've added this task:\n" + newTodo + "\n" + "Now you have " + tasks.getTasks()
                        .size() + " tasks in the list.";
                storage.OverwriteFile(tasks.getTasks());
                break;
            }
            case DEADLINE:
                String[] deadlineInput = parser.parseDeadline(input);
                Deadline newDeadline = new Deadline(deadlineInput[0], deadlineInput[1]);
                tasks.addTask(newDeadline);
                response = "Got it. I've added this task:\n" + newDeadline + "\n" + "Now you have " + tasks.getTasks()
                        .size() + " tasks in the list.";
                storage.OverwriteFile(tasks.getTasks());
                break;
            case EVENT:
                String[] eventInput = parser.parseEvent(input);
                Event newEvent = new Event(eventInput[0], eventInput[1]);
                tasks.addTask(newEvent);
                response = "Got it. I've added this task:\n" + newEvent + "\n" + "Now you have " + tasks.getTasks()
                        .size() + " tasks in the list.";
                storage.OverwriteFile(tasks.getTasks());
                break;
            case FIND:
                String word = parser.parseFind(input);
                response = ui.listTasksAsString(tasks.findTasks(word));
                break;
            case ERROR:
                throw new DukeException("Sorry :( I don't know what this means.");
            }
            return response;
        } catch (DukeException e) {
            return e.getErrorMsg();
        }
    }
}