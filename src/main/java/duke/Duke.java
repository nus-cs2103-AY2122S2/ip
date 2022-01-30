package duke;

import java.util.Scanner;

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

import duke.command.Parser;
import duke.io.Storage;
import duke.task.TaskList;
import duke.ui.Ui;



/**
 * Main Class for Duke.
 */
public class Duke extends Application {
    Ui ui = new Ui(null, null);
    Scanner in = new Scanner(System.in);
    Parser parser = new Parser();
    TaskList taskList = new TaskList();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/jerry.png"));
    private Image user = new Image(this.getClass().getResourceAsStream("/images/tom.jpg"));


    /**
     * Main method for Duke.
     *
     * @param args The arguments to run Duke with.
     */
    public static void main(String[] args) {

//        Storage.loadFile(System.getProperty("user.dir") + "\\data\\duke.txt", taskList);
//
//        ui.printLogo();
//        String userInput = in.nextLine();
//        int runResult = parser.run(userInput, ui, taskList);
//        while (runResult != -1) {
//            if (runResult == 1) {
//                Storage.saveFile("data", "duke.txt", taskList.getList());
//            }
//            userInput = in.nextLine();
//            runResult = parser.run(userInput, ui, taskList);
//        }
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

        ui = new Ui(dialogContainer, duke);
        Storage.loadFile(System.getProperty("user.dir") + "\\data\\duke.txt", taskList);
        ui.printLogo();
    }

    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user))
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        parser.run(userInput.getText(), ui, taskList);
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

}



