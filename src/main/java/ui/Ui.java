package ui;

import command.Command;
import exception.DukeException;
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

    private final Image user = new Image(this.getClass().getResourceAsStream("/DaUser.jpg"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/DaDuke.png"));

    private final Storage storage;
    private final TaskList taskList;

    /** Uses Ui, Storage and TaskList. */
    public Ui() throws DukeException {
        storage = new Storage();
        taskList = new TaskList();
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

        //Adding functionality
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private void handleUserInput() { //set up to handle UI
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
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
