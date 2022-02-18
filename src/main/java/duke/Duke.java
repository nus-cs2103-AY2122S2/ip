package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
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


/**
 * Duke runs a task tracker to help keep track of tasks. Tasks can be marked as done or not done,
 * and can be added and deleted from the task list.
 */
public class Duke extends Application {

    private Storage storageTask;
    private Storage storagePlace;
    private TaskList tasks;
    private PlaceList places;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Default duke constructor
     */
    public Duke() {
    }

    /**
     * Constructs an instance of Duke.
     *
     * @param filePathTask A string representing the path to the file to save tasks to.
     * @param filePathPlace A string representing the path to the file to save places to.
     * @throws IOException if an I/O error occurs.
     */
    public Duke(String filePathTask, String filePathPlace) throws IOException {
        ui = new Ui();
        storageTask = new Storage(filePathTask);
        try {
            if (storageTask.load().equals("")) {
                throw new DukeException("");
            }
            tasks = new TaskList(storageTask.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        storagePlace = new Storage(filePathPlace);
        try {
            if (storagePlace.load().equals("")) {
                throw new DukeException("");
            }
            places = new PlaceList(storagePlace.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            places = new PlaceList();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {

        //Solution below adopted from https://se-education.org/guides/tutorials/javaFx.html
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

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(800.0);
        stage.setMinWidth(600.0);

        mainLayout.setPrefSize(600.0, 800.0);

        scrollPane.setPrefSize(585, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(525.0);

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

        sendButton.setOnMouseClicked((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
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
     *
     * @throws IOException if an I/O error occurs.
     */
    private void handleUserInput() throws IOException {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * Generates a response to the user input.
     *
     * @return a String representing the response.
     * @throws IOException if an I/O error occurs.
     */
    private String getResponse(String input) throws IOException {
        if (input.equals("bye")) {
            Platform.exit();
        }
        return new Duke("data/tasks.txt", "data/places.txt").run(input);
    }

    /**
     * Runs the task tracker.
     *
     * @param fullCommand a string representing the user input.
     * @throws IOException if an I/O error occurs.
     */
    public String run(String fullCommand) throws IOException {
        Command command = Parser.parse(fullCommand, ui);
        return command.execute(tasks, places, ui, storageTask, storagePlace);
    }
}
