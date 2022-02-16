package duke.duke;

import duke.ui.DukeException;
import duke.ui.InputHandler;
import duke.ui.DialogBox;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Scanner;

public class Duke extends Application {

    //For GUI
    private ScrollPane scrollPane;
    private VBox dialogBox;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    //Images for Duke & user
    private Image user = new Image(this.getClass().getResourceAsStream("/images/human.jpg"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/bear.jpg"));


    public static void main(String[] args) throws IOException {
        String dukeGreeting = "Hello! I'm Duke \nWhat can I do for you?";
        String endMessage = "Bye. Hope to see you again soon!";

        System.out.println(dukeGreeting);
        Scanner sc = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        String response = "";
        while (!response.equals(endMessage)) {
            try {
                String input = sc.nextLine();
                System.out.println(response);
                response = inputHandler.handleInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * Start the application
     * @param stage Stage object
     */
    @Override
    public void start(Stage stage) {
        //Setting up required components
        Label dukeLabel = new Label("Duke");

        //Creating container for the chat to scroll
        scrollPane = new ScrollPane();
        dialogBox = new VBox();
        scrollPane.setContent(dialogBox);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        //Formatting window
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

        dialogBox.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        userInput.setPrefHeight(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Set functionality on User Input
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //scroll down if dialogBox's height changes
        dialogBox.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Creates the dialogboxes as well as handles user and duke's inputs
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogBox.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Returns the Response of Duke to user input
     *
     * @param input user's input
     * @return Duke's reply to user's input
     */
    public String getResponse(String input) {
        String output = "";
        try {
            InputHandler inputHandler = new InputHandler();
            output = inputHandler.handleInput(input);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return e.getMessage();
        }

        return output;
    }

    /**
     * Returns a Label around the text
     *
     * @param text text for the dialog
     * @return Label object with text
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }


}









