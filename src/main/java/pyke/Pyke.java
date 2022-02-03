package pyke;

import pyke.command.Command;
import pyke.exception.EmptyDescriptionException;
import pyke.exception.InvalidCommandException;
import pyke.exception.InvalidNumberException;
import pyke.exception.PykeException;
import pyke.ui.DialogBox;
import pyke.ui.Ui;
import pyke.util.Parser;
import pyke.util.Storage;
import pyke.util.TaskList;

import java.io.IOException;
import java.time.format.DateTimeParseException;

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


public class Pyke extends Application {

    private Ui ui;
    private TaskList taskList;
    private Parser parser;
    private Storage storage;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image pyke = new Image(this.getClass().getResourceAsStream("/images/DaPyke.png"));

    public Pyke() {
        ui = new Ui();
        taskList = new TaskList();
        parser = new Parser();
        storage = new Storage("data", "TaskList.txt");
    }


    /**
     * The main body of the chat box. Will receive commands and do things accordingly
     */

    public void run() {
        ui.sayGreeting();
        boolean exitFlag = false;
        try {
            storage.init(taskList);
        } catch (IOException e) {
            ui.outputException("Oops, seems like there is an error when reading local saved files");
        }
        while(!exitFlag) {
            try {
                Command c = parser.parseCommand(ui.getCommand());
                c.execute(taskList, ui, storage);
                exitFlag = c.isExit();
            } catch (InvalidCommandException e) {
                ui.outputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidNumberException e) {
                ui.outputException("OOPS!!! Seems like this is a invalid number :-(");
            } catch (EmptyDescriptionException e) {
                ui.outputException("OOPS!!! The description cannot be empty. :-(");
            } catch (DateTimeParseException e){
                ui.outputException("OOPS!!! Please enter date in yyyy-mm-dd style. (e.g. 2002-06-25)");
            } catch (IOException e) {
                ui.outputException("Oops, seems like there is an error when writing to local saved files");
            } catch (PykeException e) {
                e.printStackTrace();
            }
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
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Pyke");
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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //enable auto scroll
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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(pyke))
        );
        userInput.clear();
    }

    /**
     * Generate a response to user input.
     */
    private String getResponse(String input) {
        return "Pyke: " + input;
    }

    public static void main(String[] args) {
        new Pyke().run();
    }
}

