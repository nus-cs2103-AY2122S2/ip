package Duke;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;

public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;
    private Ui uiPrinter;
    private Parser commandParser = new Parser();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {
    }

    public Duke(String filePath) {
        uiPrinter = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTaskListFromFile());
        } catch (DukeException e) {
            e.getMessage();
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run(){
        System.out.println(uiPrinter.showWelcomeMessage());
        boolean isRunning = true;
        while(isRunning) {
            String userInput = uiPrinter.readUserInput();
            try {
                String dukeHears = commandParser.parseCommand(userInput, taskList, uiPrinter, storage);
                if (dukeHears.equals(uiPrinter.byeMsg)) {
                    isRunning = false;
                } else {
                    continue;
                }
            }
            catch(DukeException e){
                e.getMessage();
            }
        }

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

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

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
            try {
                handleUserInput();
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });

        userInput.setOnAction((event) -> {
            try {
                handleUserInput();
            } catch (DukeException e) {
                e.printStackTrace();
            }
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() throws DukeException {
        String userText = userInput.getText();
        String dukeText = commandParser.parseCommand(userText, taskList, uiPrinter, storage);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {

        uiPrinter = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            taskList = new TaskList(storage.loadTaskListFromFile());
        } catch (DukeException e) {
            e.getMessage();
            taskList = new TaskList();
        }

    try {
        String dukeOutput = commandParser.parseCommand(input, taskList, uiPrinter, storage);
        return dukeOutput;
    } catch (DukeException e) {
        return e.getMessage();
    }
    }
}