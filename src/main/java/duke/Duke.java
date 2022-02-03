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
    private DialogContainer dialogContainer;

    /**
     * Default constructor for Duke
     */
    public Duke() {
        ui = new Ui();
        dialogContainer = new DialogContainer();
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
        dialogContainer = new DialogContainer();
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
        dialogContainer.init(stage);

        dukeSayMessage("Hello! I am Duke :)");

        dialogContainer.sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        dialogContainer.userInput.setOnAction((event) -> {
            handleUserInput();
        });
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
        String userText = dialogContainer.userInput.getText();
        String dukeText = getResponse(dialogContainer.userInput.getText());

        dialogContainer.addChatBox(userText, dukeText);
    }

    private void dukeSayMessage(String text) {
        dialogContainer.addDukeChatBox(text);
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