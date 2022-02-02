package pikabot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pikabot.command.Command;
import pikabot.task.Task;


/**
 * Runs the whole application PikaBot, an application
 * that serves as a ChatBot to keep track of your tasks.
 */
public class PikaBot extends Application {

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bot = new Image(this.getClass().getResourceAsStream("/images/DaPikaBot.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
        stage.setTitle("PikaBot");
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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add.
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
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
        Label botText = new Label(getResponse(userInput.getText()));

        HBox userDialog = DialogBox.getUserDialog(userText, new ImageView(user));
        HBox botDialog = DialogBox.getBotDialog(botText, new ImageView(bot));

        userDialog.setPadding(new Insets(10));
        userDialog.setSpacing(5);
        userDialog.setStyle("-fx-background-color: #E0F0F3");

        botDialog.setPadding(new Insets(10));
        botDialog.setSpacing(5);
        botDialog.setStyle("-fx-background-color: #FFF2CC ");

        dialogContainer.getChildren().addAll(
                userDialog,
                botDialog
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        System.out.println(input + "hi");
        String[] strInputArr = input.split(" ", 2);
        String response = "";

        if (!strInputArr[0].equals("bye")) {
            Command command = Parser.parseCommand(strInputArr);
            response = command.execute(taskList, storage);
        } else {
            response = Ui.printClosingText();
        }

        return response;
    }

    private static final String FILEPATH = "data/tasks.txt";
    private Storage storage;
    private TaskList taskList;

    public PikaBot() {
        this.storage = new Storage(FILEPATH);
        this.taskList = new TaskList(new ArrayList<Task>());
        if (storage.doesFileExist()) {
            try {
                taskList = Storage.fileToTaskList(new File(FILEPATH));
            } catch (FileNotFoundException e) {
                Ui.printExceptionMessage(e);
            }
        }
    }

    /**
     * Boots up PikaBot application.
     *
     * @param args Command line arguments entered by user.
     */
    /*
    public static void main(String[] args) {

        PikaBot pikaBot = new PikaBot();
        Scanner sc = new Scanner(System.in);
        System.out.println(Ui.printWelcomeText());

        String input = sc.nextLine();
        String[] strInputArr = input.split(" ", 2);

        while (!strInputArr[0].equals("bye")) {
            Command command = Parser.parseCommand(strInputArr);
            command.execute(pikaBot.taskList, pikaBot.storage);
            input = sc.nextLine();
            strInputArr = input.split(" ", 2);
        }

        System.out.println(Ui.printClosingText());
        sc.close();
    }
    */
}
