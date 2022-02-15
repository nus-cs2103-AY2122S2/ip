package ui;

import command.Command;
import exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import storage.Storage;
import task.TaskList;

/** Main class from which bot is directly run. */
public class Ui extends Application {
    private AnchorPane mainLayout;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/User.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/Objection.jpg"));

    private final Storage storage;
    private final TaskList taskList;
    private UiFormatter uiFormatter;

    /** Uses Ui, Storage and TaskList. */
    public Ui() throws DukeException {
        storage = new Storage();
        taskList = new TaskList();
        uiFormatter = new UiFormatter();

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
    }

    @Override
    public void start(Stage stage) {
        UiFormatter uiFormatter = new UiFormatter();
        uiFormatter.formatGui(stage, mainLayout, scrollPane,
                dialogContainer, userInput, sendButton, scene);
        stage.setScene(scene);
        stage.show();

        // Start message
        initialise();

        //Adding functionality
        sendButton.setOnMouseClicked((event) -> handleUserInput(stage));
        userInput.setOnAction((event) -> handleUserInput(stage));
    }

    private void initialise() {
        Label acknowledgement = new Label("UI design inspired by @jonfoocy");
        acknowledgement.setTextFill(Color.PURPLE);
        dialogContainer.getChildren().add(acknowledgement);
        dialogContainer.setAlignment(Pos.TOP_CENTER);

        String introduction = uiFormatter.helloMessage();
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(introduction, duke, "Phoenix"));
    }

    private void handleUserInput(Stage stage) {
        String input = userInput.getText();
        if (input.trim().equals("bye")) {
            exit(stage);
            return;
        }
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, user, "User"),
                DialogBox.getDukeDialog(response, duke, "Phoenix")
        );
        userInput.clear();
    }

    private void exit(Stage stage) {
        String exitMessage = uiFormatter.exitMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog("bye", user, "User"),
                DialogBox.getDukeDialog(exitMessage, duke, "Phoenix"));

        // @@author MonthPython28-reused
        // Reused from https://github.com/jonfoocy/ip/blob/master/src/main/java/MainWindow.java
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished((event) -> stage.close());
        delay.play();
    }

    private String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(storage, taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
