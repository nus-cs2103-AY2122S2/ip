package duke;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

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


public class Duke extends Application {

    private Storage storage;
    private TaskList list = new TaskList(new ArrayList<>());
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.tx");
        try {
            list = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.print("Error: ");
            System.out.println(e.getMessage());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    private String handleTodo(String input) {
        Todo t = new Todo(input.substring(5));
        list.add(t);
        String out = ("Got it. I've added: \n" + t.toString() + "\n");
        out += String.format("Sheesh you've now got %d tasks in the list\n", list.size());
        return out;
    }

    /**
     * Handler for creating a Deadline
     *
     * @param out The String to be appended to
     * @param parsedInput The user's input containing the task description and deadline
     * @return he string output to be shown to the user
     * @throws DukeException if  the deadline is not in the correct format
     */
    private String handleDeadline(String out, String[] parsedInput) throws DukeException {
        String[] date = parsedInput[1].split("-");
        if (date.length != 3) {
            throw new DukeException("Your deadline should be in the format yyyy-mm-dd");
        }
        try {
            Deadline d = new Deadline(parsedInput[0],
                    LocalDate.of(Integer.parseInt(date[0]),
                            Integer.parseInt(date[1]),
                            Integer.parseInt(date[2])));
            list.add(d);
            out += ("Got it. I've added: \n" + d.toString() + "\n");
            out += String.format("Sheesh you've now got %d tasks in the list\n", list.size());
        } catch (DateTimeException e) {
            throw new DukeException("Your date should be in the format yyyy-mm-dd");
        }
        return out;
    }

    private String handleEvent(String[] arr) {
        Event e = new Event(arr[0], arr[1]);
        list.add(e);
        String out = ("Got it. I've added: \n" + e.toString() + "\n");
        out += String.format("Sheesh you've now got %d tasks in the list\n", list.size());
        return out;
    }

    private String handleDelete(int idx) {
        Task t = list.remove(idx - 1);
        String out = String.format("Alrighty I've removed this task\n");
        out += (t.toString() + "\n");
        out += String.format("K you've now got %d tasks in the list\n", list.size());
        return out;
    }

    private String handleHelp() {
        String out = "Here is a list of commands to help you get started:\n"
                + "* todo: Adds a task to be done Eg: todo Buy groceries\n"
                + "* deadline: Adds a task to be done by a certain deadline.\n "
                + "Use the format yyyy-mm-dd. Eg: deadline Finish studying /by 2022-02-28\n"
                + "* event: Adds an event occurring at a specific time Eg: event Lecture /at Thursday 2-4pm\n"
                + "* list: List all the tasks Eg: list\n"
                + "* mark: Mark a task as done Eg: mark 3\n"
                + "* unmark: Mark a task as not done Eg: unmark 3\n"
                + "* delete: Delete a task Eg: delete 4\n"
                + "* find: Find a task by searching for a substring Eg: find Lecture\n"
                + "* save: Saves the tasks that have been added in the current session to local storage";
        return out;
    }

    /**
     * Runs the Price command that is given
     *
     * @param input The command to be run
     * @throws DukeException if the command is formatted wrongly
     */
    private String handle(String input) throws DukeException {
        String[] split = input.split(" ");
        String command = split[0];
        String out = "";
        if (command.equals("todo")) {
            if (split.length == 1) {
                throw new DukeException("It looks like you're missing the task description");
            } else {
                out = handleTodo(input);
            }
        } else if (command.equals("deadline")) {
            if (split.length == 1) {
                throw new DukeException("It looks like you're missing the task description");
            } else {
                //parsedInput is the input string with the deadline and the task description separated
                String[] parsedInput = input.substring(9).split("/by ");
                if (parsedInput.length == 1) {
                    throw new DukeException("It seems you haven't included the deadline");
                } else {
                    out = handleDeadline(out, parsedInput);
                }
            }
        } else if (command.equals("event")) {
            if (split.length == 1) {
                throw new DukeException("It looks like you're missing the task description");
            } else {
                String[] parsedInput = input.substring(6).split("/at ");
                if (parsedInput.length == 1) {
                    throw new DukeException("It seems you haven't included the event time");
                } else {
                    out = handleEvent(parsedInput);
                }
            }
        } else if (command.equals("save")) {
            boolean isSuccess = false;
            try {
                isSuccess = storage.store(list);
            } catch (IOException e) {
                out += e.getMessage();
            }
            assert isSuccess;
            out += ("Your tasks have been saved successfully :)");
        } else if (command.equals("list")) {
            out = list.printList();
        } else if (command.equals("mark") || command.equals("unmark")) {
            if (split.length <= 1) {
                throw new DukeException("You need to specify which task :/");
            }
            int idx = Integer.parseInt(split[1]);
            if (idx > list.size()) {
                throw new DukeException("The task number you've given is invalid");
            } else {
                Task t = list.get(idx - 1);
                if (command.equals("unmark")) {
                    t.makeNotDone();
                    out += String.format("Ok boss I've marked task %s as incomplete\n", split[1]);
                } else {
                    t.makeDone();
                    out += String.format("Woohoo! I've marked task %s as done\n", split[1]);
                }
                out += (t.toString() + "\n");
            }
        } else if (command.equals("delete")) {
            if (list.size() == 0) {
                throw new DukeException("There aren't any tasks left to delete");
            } else {
                if (split.length <= 1) {
                    throw new DukeException("You need to specify which task :/");
                }
                int idx = Integer.parseInt(split[1]);
                if (idx > list.size()) {
                    throw new DukeException("The task number you've given is invalid");
                } else {
                    out = handleDelete(idx);
                }
            }
        } else if (command.equals("find")) {
            if (list.size() == 0) {
                throw new DukeException("There aren't any tasks search :)");
            }
            String searchString = split[1];
            out = list.search(searchString);
        } else if (command.equals("help")) {
            out = handleHelp();
        } else {
            throw new DukeException("I'm not sure what that means");
        }
        assert !out.equals("");
        return out;
    }

    @Override
    public void start(Stage stage) throws DukeException {
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
        stage.setTitle("Prince");
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

        printWelcome();

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void printWelcome() {
        String welcome = "Hey there! I'm Prince :) \n"
                + "How can I help you today?\n"
                + "(type help if you're confused)\n";
        Label dukeText = new Label(welcome);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
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


    private String getResponse(String input) {
        try {
            return handle(input);
        } catch (DukeException e) {
            return ui.showError(e);
        }
    }

}
