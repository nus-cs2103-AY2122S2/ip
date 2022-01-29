package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Main class
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Runs the Duke Program
     *
     * @param args No Args needed
     */
    public static void main(String[] args) {
        Ui.printWelcome();

        TaskList list = DataStore.loadData();

        Command actionType = Ui.getCommand();
        String[] parsedInput = Ui.getInputs();

        while (actionType != Command.BYE) {
            int indexOfList = -1;
            Boolean wasAddSuccess = null;


            switch (actionType) {
            case LIST:
                list.print();
                break;
            case MARK:
                indexOfList = Integer.parseInt(parsedInput[0]);
                list.markComplete(indexOfList);
                System.out.println("Marked as complete");
                list.print(indexOfList);
                break;
            case UNMARK:
                indexOfList = Integer.parseInt(parsedInput[0]);
                list.markIncomplete(indexOfList);
                System.out.println("Marked as incomplete");
                list.print(indexOfList);
                break;
            case EVENT:
                try {
                    wasAddSuccess = list.add(TaskType.EVENT, parsedInput);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Description of task can't be empty");
                    wasAddSuccess = false;
                }
                if (wasAddSuccess) {
                    System.out.println("added: " + parsedInput[0].strip());
                } else {
                    System.out.println("Failed to add " + parsedInput[0].strip());
                }
                System.out.print("Number of tasks: ");
                System.out.println(list.getLength());
                break;
            case DEADLINE:
                try {
                    wasAddSuccess = list.add(TaskType.DEADLINE, parsedInput);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Description of task can't be empty");
                    wasAddSuccess = false;
                }
                if (wasAddSuccess) {
                    System.out.println("added: " + parsedInput[0].strip());
                } else {
                    System.out.println("Failed to add " + parsedInput[0].strip());
                }
                System.out.print("Number of tasks: ");
                System.out.println(list.getLength());
                break;
            case TODO:
                try {
                    wasAddSuccess = list.add(TaskType.TODO, parsedInput);
                } catch (EmptyDescriptionException e) {
                    System.out.println("Description of task can't be empty");
                    wasAddSuccess = false;
                }
                if (wasAddSuccess) {
                    System.out.println("added: " + parsedInput[0].strip());
                } else {
                    System.out.println("Failed to add " + parsedInput[0].strip());
                }
                System.out.print("Number of tasks: ");
                System.out.println(list.getLength());
                break;
            case DELETE:
                indexOfList = Integer.parseInt(parsedInput[0]);
                System.out.println("Noted. I've removed this task:");
                list.print(indexOfList);
                list.delete(indexOfList);
                System.out.println("Now you have " + Integer.toString(list.getLength()) + " tasks in the list.");
                break;
            case FIND:
                list.findTask(parsedInput[0]);
                break;
            default:
                break;
            }

            DataStore.saveData(list);

            actionType = Ui.getCommand();
            parsedInput = Ui.getInputs();
        }

        System.out.println("Bye. Hope to see you again soon!");

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

//Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

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
