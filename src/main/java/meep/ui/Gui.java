package meep.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import meep.commands.Command;
import meep.exception.InvalidInputException;
import meep.parser.Parser;
import meep.storage.Storage;
import meep.task.ListTask;


public class Gui extends Application {

    private Image userLogo = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private Image meepLogo = new Image(this.getClass().getResourceAsStream("/images/meep.jpeg"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Storage storage;
    private Parser parser;
    private ListTask taskList;

    public static void main(String[] args) {


    }

    public void setup() throws InvalidInputException {
        try {
            this.parser = new Parser();
            this.taskList = new ListTask();
            this.storage = new Storage("data.txt", taskList);
        } catch (IOException | InvalidInputException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) {


        //Step 1. Setting up required components
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button();


        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Meep");
        stage.setResizable(false);
        stage.setMinHeight(6000.0);
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
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
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
                DialogBox.getUserDialog(userText.getText(), userLogo),
                DialogBox.getMeepDialog(dukeText.getText(), meepLogo)
        );
        userInput.clear();
    }


    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        String result = "";

        try {
            Command command = parser.parseUserInput(input, taskList.getList());
            result = command.execute(taskList);
        } catch (InvalidInputException e) {
            result = e.getMessage();
        }
        return result;
    }


    /**
     * Generates task list.
     */
    public String showTasks() {
        if (taskList.isEmpty()) {
            return "You have no task currently.\n Please try to add new task \n";
        } else {
            return "These are your current tasks:\n" + taskList.generateTaskList();
        }
    }


    /**
     * Saves task to storage file.
     */
    public boolean saveTaskToFile() {
        try {
            storage.saveTaskToFile(taskList.getList());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Create data file.
     */
    public void createDataFile() throws InvalidInputException {
        try {
            storage.checkFileExists();
        } catch (InvalidInputException e) {
            throw new InvalidInputException(e.getMessage());
        }
    }
}
