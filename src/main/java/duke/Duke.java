package main.java.duke;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke extends Application {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    //private Image user = new Image(this.getClass().getResourceAsStream("/images/User.jpg"));
    //private Image duke = new Image(this.getClass().getResourceAsStream("/images/Duke.jpg"));
//
//    /**
//     * Constructs an instance of Duke.
//     * Sets up UI, Storage, TaskList tagged to instance of Duke.
//     *
//     * @param filePath Path where storage is located or to be created.
//     */
//    public Duke(String filePath) {
//        ui = new Ui();
//        storage = new Storage(filePath);
//        try {
//            tasks = new TaskList(storage.load());
//            ui.showTasksLoaded(tasks);
//        } catch (IOException | ArrayIndexOutOfBoundsException e) {
//            ui.showError("LoadingError");
//            tasks = new TaskList();
//        }
//    }
//
//    /**
//     * Runs that instance of Duke.
//     * Allows users to interact with Duke in CLI.
//     */
//    public void run() {
//        ui.welcome();
//        boolean end = false;
//        while (!end) {
//            try {
//                String fullCommand = ui.readCommand();
//                ui.horizontal(); // show the divider line ("_______")
//                Command c = Parser.parse(fullCommand, ui);
//                c.execute(tasks, ui, storage);
//                end = c.isEnd();
//            } catch (UnsupportedOperationException e) {
//                ui.showError("UnknownCommand");
//            } finally {
//                ui.horizontal();
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        new Duke("data/tasks.txt").run();
//    }
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText),
                new DialogBox(dukeText)
        );
        userInput.clear();
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

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

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        scene = new Scene(mainLayout);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();

    }
}
