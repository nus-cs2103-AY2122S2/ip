package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.lang.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Programme which serves as an interactive checklist.
 */
public class Duke extends Application {

    private duke.Storage storage;
    private duke.TaskList tasks;
    private duke.Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/bulbasaur.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/pikachu.png"));

    /**
     * Initializes Duke.
     * @param filePath represents the path of the file and existing tasks to be loaded
     *                 if already present.
     */
    public Duke(String filePath) {
        ui = new duke.Ui();
        storage = new duke.Storage(filePath);
        try {
            //tasks = new TaskList(storage.load());
            tasks = new duke.TaskList();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new duke.TaskList();
        }
    }

    /**
     * Initializes Duke.
     *
     */
    public Duke() {
        ui = new duke.Ui();
        try {
            //tasks = new TaskList(storage.load());
            tasks = new duke.TaskList();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new duke.TaskList();
        }
    }

    /**
     * Runs through live user input to add, edit the tasks.
     */
    void run() {
        ui.start();

        Scanner sc = new Scanner(System.in);


        while (sc.hasNextLine()) {
            String value = sc.nextLine();
            String[] splitStr = value.split("\\s+");

            if (value.equals("bye")) {
                ui.finalBye();
                return;

            } else if (value.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }

            } else if (splitStr[0].equals("mark")) {
                int index = Integer.parseInt(splitStr[1]);
                duke.Task task = (duke.Task) tasks.get(index - 1);
                task.markAsDone();
                ui.markDone(tasks.get(index - 1));


            } else if (splitStr[0].equals("unmark")) {
                int index = Integer.parseInt(splitStr[1]);
                duke.Task task = (duke.Task) tasks.get(index - 1);
                task.unmarkAsDone();
                ui.unmarkDone(tasks.get(index - 1));

            } else if (splitStr[0].equals("delete")) {
                int index = Integer.parseInt(splitStr[1]);
                duke.Task task = (duke.Task) tasks.get(index - 1);
                tasks.remove(index - 1);
                ui.removedTask(task, tasks);

            } else if (splitStr[0].equals("find")) {
                String findString = value.substring(5);
                duke.TaskList foundTasks = new duke.TaskList();
                for (int i =0; i<tasks.size(); i++) {
                    if (tasks.get(i).toString().contains(findString)) {
                        foundTasks.add(tasks.get(i));
                    }
                }
                ui.findTasks(foundTasks);

            } else if (splitStr[0].equals("todo") || splitStr[0].equals("deadline") || splitStr[0].equals("event")) {

                String[] parts = value.split("/");
                String description = parts[0];
                if (parts.length > 1) {
                    if (parts[1].length() == 13) {
                        LocalDate d1 = LocalDate.parse(parts[1].substring(3));
                        parts[1] = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                    }
                    description += "(" + parts[1] + ")";
                }

                try {
                    if (splitStr[0].equals("todo")) {
                        description = description.substring(5);
                        tasks.add(new duke.ToDo(description));
                    }
                } catch (Exception e) {
                    ui.emptyInput();
                }
                try {
                    if (splitStr[0].equals("deadline")) {
                        description = description.substring(9);
                        tasks.add(new duke.Deadline(description));
                    }
                } catch (Exception e) {
                    ui.emptyInput();
                }
                try {
                    if (splitStr[0].equals("event")) {
                        description = description.substring(6);
                        tasks.add(new duke.Event(description));
                    }
                } catch (Exception e) {
                    ui.emptyInput();
                }

                ui.addTask(tasks);

            } else {
                ui.doNotUnderstand();

            }
            storage.save(tasks);
        }
    }

    /**
     * Creates an instance of Duke and runs it.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
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

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
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
        dialogContainer.getChildren().addAll(
                new duke.DialogBox(userInput.getText(), user),
                new duke.DialogBox(getResponse(userInput.getText()),duke)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Pikachu heard you say: " + input;
    }


}








