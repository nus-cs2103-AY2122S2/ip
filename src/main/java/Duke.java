package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Programme which serves as an interactive checklist.
 */
public class Duke extends Application {

    private duke.Storage storage = new duke.Storage();
    private duke.TaskList tasks;
    private duke.Ui ui;
    private duke.Parser parser = new duke.Parser();

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
            tasks = new duke.TaskList(storage.load());
            //tasks = new duke.TaskList();
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new duke.TaskList(storage.load());
        }
    }

    /**
     * Runs through live user input to add, edit the tasks.
     */
    void run() {
        ui.start();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String values = sc.nextLine();
            tasks = parser.parse(ui, tasks, values);
            if (values.equals("bye")) {
                return;
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
     *
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        dialogContainer.getChildren().addAll(
                new duke.DialogBox(userInput.getText(), user),
                new duke.DialogBox(getResponse(userInput.getText()), duke)
        );
        userInput.clear();
    }

    /**
     * Interactive echo for the bot to repeat the statement to the user.
     * @param input Input as typed by the user.
     * @return String to represent pikachu's echo of the user input.
     */
    public String getResponse(String input) {
        return "Pikachu heard you say: " + input;
    }


}








