package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class Ui extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private String welcome = "Welcome to Duke! What can I do for you today? \n";
    private Label label = new Label(welcome);
    private Parser parser = new Parser();
    private TaskList taskList = Duke.tasks;


    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, label);

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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        // more code to be added here later
    }

    private void handleUserInput() {

        this.label.setVisible(false);
        String userText = userInput.getText();
        String response = "";
        CommandType commandType = parser.parse(userText);

        switch (commandType) {
        case LIST:
            response = taskList.getTasksAsString();
            break;
        case MARK:
            try {
                String[] splitted = userText.split(" ");
                int index = Integer.valueOf(splitted[1]);
                Task set = taskList.setDone(index - 1);
                response += "Nice! I've marked this task as done: \n";
                response += set;
                break;
            } catch (IndexOutOfBoundsException e) {
                response += "ERROR: The index you have provided is invalid";
                break;
            }
        case UNMARK:
            try {
                String[] splitted = userText.split(" ");
                int index = Integer.valueOf(splitted[1]);
                Task unset = taskList.setUndone(index - 1);
                response += "OK, I've marked this task as not done yet:  \n";
                response += unset;
                break;
            } catch (IndexOutOfBoundsException e) {
                response += "ERROR: The index you have provided is invalid";
                break;
            }
        case TODO:
            try {
                Task toAdd = new Todo(userText.split("todo")[1].trim());
                taskList.addTask(toAdd);
                response += "Got it. I've added this task: \n";
                response += toAdd + "\n";
                response += "Now you have " + taskList.getSize() + " tasks in the list.";
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                response = "☹ OOPS!!! The description of a todo cannot be empty.";
                break;
            }
        case DEADLINE:
            try {
                String[] splitted = userText.split("deadline")[1].split("/by");
                Deadline toAdd = new Deadline(splitted[0].trim(), splitted[1].trim());
                taskList.addTask(toAdd);
                response += "Got it. I've added this task: \n";
                response += toAdd + "\n";
                response += "Now you have " + taskList.getSize() + " tasks in the list.";
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                response += "Oops! The correct format is : `deadline /by <type time here>`";
                break;
            }
        case EVENT:
            try {
                String[] splitted = userText.split("event")[1].split("/at");
                Event toAdd = new Event(splitted[0].trim(), splitted[1].trim());
                taskList.addTask(toAdd);
                response += "Got it. I've added this task: \n";
                response += toAdd + "\n";
                response += "Now you have " + taskList.getSize() + " tasks in the list.";
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                response += "Oops! The correct format is : `event /at <type time here>`";
                break;
            }
        case DELETE:
            try {
                String[] splitted = userText.split(" ");
                int index = Integer.valueOf(splitted[1]);
                Task removedTask = taskList.deleteTask(index - 1);
                response += "Noted. I've removed this task: \n";
                response += removedTask + "\n";
                response += "Now you have " + taskList.getSize() + " tasks in the list.";
                break;
            } catch (IndexOutOfBoundsException e) {
               response += "The index you have provided is invalid";
                break;
            }
        case FIND:
            String[] splitted = userText.split(" ");
            response += "Here are the matching tasks in your list:\n";
            taskList.findAndGetTasks(splitted[1]);
            break;
        case UPDATE:
            String[] splittedString = userText.split(" ");
            int index = Integer.valueOf(splittedString[1]);
            String command = splittedString[2];
            if (command.startsWith("name")) {
                String name = userText.split("name")[1].trim();
                taskList.findAndSetName(name, index);
                response += "Noted. I have updated the name of task " + index + " to " + name + ".";
            }
            if (command.startsWith("time")) {
                String time = splittedString[3].trim();
                if (splittedString.length == 5) {
                    time += " " + splittedString[4].trim();
                }
                response += "Noted. I have updated the time of task " + index + " to " + time + ".";
                taskList.findAndSetTime(time, index);
            }


        default:
            break;
        }

        Label userTextLabel = new Label(userText);
        Label dukeTextLabel = new Label(response);
        dialogContainer.getChildren().addAll(
                new DialogBox(userTextLabel, new ImageView(user)),
                new DialogBox(dukeTextLabel, new ImageView(duke)).flip()
        );
        userInput.clear();
        /*
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
         */
    }



}
