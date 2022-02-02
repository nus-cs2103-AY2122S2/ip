package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Duke class that contains the main method to run Duke.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/kirby1.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/kirby2.png"));


    /**
     * Override start method from the abstract Application class.
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        // Step 1. Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(
                "Hi there! 👋 I'm Duke. What can I do for you?", duke));
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Step 2. Formatting the window to look as expected
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add.
     * @return a label with the specidied text that has word wrap enabled.
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
    private void handleUserInput() throws DukeException, IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) throws DukeException, IOException {
        return reply(input);
    }
    /**
     * Main method to run Duke.
     *
     * @param
     * @throws IOException
     * @throws DukeException
     */
    public String reply(String userInput) throws IOException, DukeException {

        Ui ui = new Ui();
        ui.greet();

        // reading user input
        Scanner sc = new Scanner(System.in);

        // initializing variable
        boolean isBye = false;

        // array container
        ArrayList<Task> todoLists = new ArrayList<Task>();
        TaskList taskLists = new TaskList(todoLists);

        // initializing storage
        Storage storage = new Storage();

        // load data when duke starts up
        try {
            todoLists = storage.load();
            taskLists = new TaskList(todoLists);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (!isBye) {
            // A string to display the remaining task number
            String displayTaskAmount = String.format("Now you have %d tasks in the list.", todoLists.size() + 1);

            if (userInput.equals("bye")) {
                isBye = true;
                return "    Bye. See you again next time! Have a nice day 😊!";

            } else {
                // storing input task in todoLists
                String[] userInputArr = userInput.split(" ");
                String userCommand = userInputArr[0];
                String userInputTask = String.join(" ",
                        Arrays.copyOfRange(userInputArr, 1, userInputArr.length));

                try {
                    Parser.userCommandValidator(userCommand);
                } catch (DukeException e) {

                   return "    OOPS!!! I'm sorry, but I don't know what that means.";

                }

                switch (userCommand) {
                case "list":
                    return Parser.parserList(taskLists);
                case "todo":
                    // handle error from empty task description
                    try {
                        return Parser.parserTodo(taskLists, userInputTask, storage);
                    } catch (DukeException e) {
                        return "    OOPS!!! The description of a todo cannot be empty.";
                    }
                case "deadline":
                    // handle error from empty task description
                    try {
                        Parser.parserDeadlineValidator(userInputTask);
                    } catch (DukeException e) {
                        if (e.getMessage().equals("The description of a deadline cannot be empty.")) {
                            return "The description of a deadline cannot be empty.";
                        } else if (e.getMessage().equals("Deadline tasks require a by day.")) {
                            return "Deadline tasks require a by day.";
                        } else if (e.getMessage().equals("Deadline tasks can only have one by day.")) {
                            return "Deadline tasks can only have one by day.";
                        }
                    }

                    // splitting deadline into description and by
                    String[] deadlineTaskArr = userInputTask.split(" /by ");
                    String[] byAndTime = deadlineTaskArr[1].split(" ");
                    String by = byAndTime[0];

                    try {
                        String deadlineTime = byAndTime[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return "    Deadline time is required";
                    }

                    String deadlineTime = byAndTime[1];

                    // handle error when time is not in the hh:mm 24hr clock format
                    try {
                        LocalTime.parse(deadlineTime);
                    } catch (DateTimeParseException e) {
                        return "    Time must be in the hh:mm 24hr format";
                    }

                    // handle error when there is invalid deadline date format
                    try {
                        Parser.deadlineDateFormatValidator(by);
                    } catch (DukeException e) {
                        return "    OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.";
                    }

                    return Parser.parserDeadline(taskLists, userInputTask, storage);

                case "event":

                    try {
                        Parser.parserEventValidator(userInputTask);
                    } catch (DukeException e) {
                        break;
                    }

                    // splitting event into description and dateTime
                    String[] eventTaskArr = userInputTask.split(" /at ");
                    String[] eventDateAndTime = eventTaskArr[1].split(" ");
                    String eventDate = eventDateAndTime[0];

                    try {
                        String eventTime = eventDateAndTime[1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        return "    Event time is required";
                    }

                    String eventTime = eventDateAndTime[1];

                    // handle error when time is not in the hh:mm 24hr clock format
                    try {
                        LocalTime.parse(eventTime);
                    } catch (DateTimeParseException e) {
                        return "    Time must be in the hh:mm 24hr format";
                    }

                    LocalTime atTime = LocalTime.parse(eventTime);

                    // handle error when there is invalid deadline date format
                    try {
                        Parser.eventDateFormatValidator(eventDate);
                    } catch (DukeException e) {
                        return "    OOPS!!! Deadline tasks can only be in the YYYY-MM-DD format.";
                    }

                    return Parser.parserEvent(taskLists, userInputTask, storage);
                case "mark":
                    return Parser.parserMark(taskLists, userInputArr, storage);
                case "unmark":
                    return Parser.parserUnmark(taskLists, userInputArr, storage);
                case "delete":
                    try {
                        Parser.parserDeleteValidator(taskLists, userInputTask);
                    } catch (DukeException e) {
                        break;
                    }
                    return Parser.parserDelete(taskLists, userInputArr, storage);
                case "find":
                    return Parser.parserFind(taskLists, userInputTask);
                }

            }
        }
        return userInput;
    }
}
