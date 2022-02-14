package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.InputMismatchException;

/**
 * Main class.
 *
 * @author sibinhho99-nus
 */
public class Duke extends Application {
    private Storage storage = new Storage();
    private Parser parser = new Parser();
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        try {
            storage.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), user),
                DialogBox.getDukeDialog(userInput.getText(), duke)
        );
        userInput.clear();
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        TaskList pastTasks = storage.getTaskList();

        try {
            String command = input.trim();

            if (parser.getType(command).equals("help")) {
                String HELP_URL = "https://github.com/sibinhho99-nus/ip";
                Desktop d = Desktop.getDesktop();
                d.browse(new URI(HELP_URL));

                return ("Please see the help page opened for more information!\n");
            } else if (parser.getType(command).equals("bye")) {
                return ("Bye. Hope to see you again soon!");
            } else if (parser.getType(command).equals("list")) {
                return (pastTasks.toString());
            } else if (parser.getType(command).equals("find")) {
                TaskList results = new TaskList();
                String nameOfTaskToFind = Parser.getTaskToFindName(command);

                for (int i = 0; i < pastTasks.size(); i++) {
                    Task currentTask = pastTasks.get(i);
                    if (currentTask.getName().contains(nameOfTaskToFind)) {
                        results.add(currentTask);
                    }
                }

                return (results.toString());
            } else if (parser.getType(command).equals("mark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                pastTasks.get(index).mark(true);

                return ("OK, I've marked this task as done:\n  "
                        + pastTasks.get(index)
                );
            } else if (parser.getType(command).equals("unmark")) {
                int index = Integer.parseInt(command.split(" ")[1]) - 1;
                pastTasks.get(index).mark(false);

                return ("OK, I've marked this task as not done yet:\n  "
                        + pastTasks.get(index)
                );
            } else if (parser.getType(command).equals("todo")) {
                if (command.length() == 4) {
                    throw new InputMismatchException("The description of a todo cannot be empty.");
                }


                pastTasks.add(parser.parseTodos(command));
                storage.save();

                return (String.format("Got it. I've added this task:\n"
                                + "%s\n"
                                + "Now you have %d %s in the list.",
                        pastTasks.get(pastTasks.size() - 1),
                        pastTasks.size(),
                        pastTasks.size() == 1 ? "task" : "tasks"
                ));
            } else if (parser.getType(command).equals("deadline")) {
                pastTasks.add(parser.parseDeadlines(command));
                storage.save();

                return (String.format("Got it. I've added this task:\n"
                                + "%s\n"
                                + "Now you have %d %s in the list.",
                        pastTasks.get(pastTasks.size() - 1),
                        pastTasks.size(),
                        pastTasks.size() == 1 ? "task" : "tasks"
                ));
            } else if (parser.getType(command).equals("event")) {
                pastTasks.add(parser.parseEvent(command));
                storage.save();

                return (String.format("Got it. I've added this task:\n"
                                + "%s\n"
                                + "Now you have %d %s in the list.",
                        pastTasks.get(pastTasks.size() - 1),
                        pastTasks.size(),
                        pastTasks.size() == 1 ? "task" : "tasks"
                ));
            } else if (parser.getType(command).equals("delete")) {
                Task removedTask = pastTasks.remove(Parser.getDeleteIndex(command));

                storage.save();

                return (String.format("Noted. I've removed this task:\n"
                                + "%s\n"
                                + "Now you have %d %s in the list.",
                        removedTask,
                        pastTasks.size(),
                        pastTasks.size() == 1 ? "task" : "tasks"
                ));
            } else {
                throw new InputMismatchException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            return (":( OOPS!!! " + e.getMessage());
        }
    }

    public void start(Stage stage) {
        //Step 1. Formatting the window to look as expected.
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }
}
