package dukeclasses;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.application.Application;
import javafx.application.Platform;


import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;

import javafx.util.Duration;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Represent a program that reads commands from user input and logs them into tasks that are
 * stored for tracking whenever the program is booted up.
 */
public class Duke extends Application {

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private static final String TEXT_DATA_FILE_PATH = "data.txt";
    private boolean isExit = false;

    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;
    private ScrollPane scrollPane;

    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(TEXT_DATA_FILE_PATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException errorMessage) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Contains and runs the main logic of Duke.
     */
    private void handleUserInput() {
        String output = ui.showInputError();
        String stringUserInput = userInput.getText();

        ParsedCommand parsedCommand = null;
        try {
            parsedCommand = Parser.parse(stringUserInput);
        } catch (DukeException errorMessage) {
            output =  ui.showInputError();

            Label userText = new Label(userInput.getText());
            Label dukeText = new Label(output);
            dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(user)));

            DialogBox dukeReply = DialogBox.getDukeDialog(dukeText, new ImageView(duke));
            Timeline delayReply = new Timeline();
            delayReply.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                    (event) -> dialogContainer.getChildren().add(dukeReply)));
            delayReply.play();
            userInput.clear();
            return ;
        }

        switch (parsedCommand.getCommand()) {
        case "hi":
            output =  ui.greet();
            break;
        case "bye":
            isExit = true;
            output =  ui.sayBye();
            break;
        case "list":
            output =  ui.listTask(storage.getStorageFilePath());
            break;
        case "mark":
            if (parsedCommand.getIndex() >= tasks.getTaskList().size()) {
                output =  ui.showInputError();
                break;
            }
            Task markedTask = tasks.updateTask(parsedCommand.getIndex(), true);
            try {
                storage.updateStorage(tasks.getTaskList());
            } catch (DukeException errorMessage) {
                output =  ui.showStorageError();
                break;
            }
            output =  ui.identifyTask(markedTask);
            break;
        case "unmark":
            if (parsedCommand.getIndex() > tasks.getTaskList().size()) {
                output =  ui.showInputError();
                break;
            }

            Task unmarkedTask = tasks.updateTask(parsedCommand.getIndex(), false);

            try {
                storage.updateStorage(tasks.getTaskList());
            } catch (DukeException errorMessage) {
                output =  ui.showStorageError();
                break;
            }
            output =  ui.identifyTask(unmarkedTask);
            break;
        case "todo":
            ToDo todo = new ToDo(parsedCommand.getTask());

            try {
                storage.appendToStorage(todo);
            } catch (DukeException errorMessage) {
                output =  ui.showStorageError();
                break;
            }

            tasks.addTask(todo);
            output =  ui.newTask(todo, tasks.getTaskList().size());
            break;
        case "event":
            Event event = new Event(parsedCommand.getTask(), parsedCommand.getDueDate());

            try {
                storage.appendToStorage(event);
            } catch (DukeException errorMessage) {
                output =  ui.showStorageError();
                break;
            }

            tasks.addTask(event);
            output =  ui.newTask(event, tasks.getTaskList().size());
            break;
        case "deadline":
            Deadline deadline = new Deadline(parsedCommand.getTask(), parsedCommand.getDueDate());

            try {
                storage.appendToStorage(deadline);
            } catch (DukeException errorMessage) {
                output =  ui.showStorageError();
                break;
            }

            tasks.addTask(deadline);
            output =  ui.newTask(deadline, tasks.getTaskList().size());
            break;
        case "delete":

            if (parsedCommand.getIndex() > tasks.getTaskList().size()) {
                output =  ui.showInputError();
                break;
            }

            Task deletedTask = null;
            try {
                deletedTask = tasks.deleteTask(parsedCommand.getIndex());
            } catch (DukeException errorMessage) {
                output = ui.showInputError();
                break;
            }

            try {
                storage.updateStorage(tasks.getTaskList());
            } catch (DukeException errorMessage) {
                output =  ui.showStorageError();
                break;
            }

            output =  ui.deleteTask(deletedTask);
            break;
        case "find":
            String taskDescription = parsedCommand.getTask();
            TaskList findTaskList = new TaskList();

            for (int i = 0; i < tasks.getTaskList().size(); i++) {
                Task task = tasks.getTaskList().get(i);
                if (task.getDescription().contains(taskDescription)) {
                    findTaskList.getTaskList().add(task);
                }
            }

            output =  ui.listTaskUsingArrayList(findTaskList);
            break;
        }

        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(output);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(user)));
        DialogBox dukeReply = DialogBox.getDukeDialog(dukeText, new ImageView(duke));

        Timeline delayReply = new Timeline();
        delayReply.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                (event) -> dialogContainer.getChildren().add(dukeReply)));
        delayReply.play();

        userInput.clear();
    }

    /**
     * Sets the interface of the GUI.
     *
     * @param stage Stage to be used for the GUI.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.setPrefSize(400.0, 600.0);

        AnchorPane.setTopAnchor(scrollPane, 0.1);

        AnchorPane.setBottomAnchor(sendButton, 0.1);
        AnchorPane.setRightAnchor(sendButton, 0.1);

        AnchorPane.setLeftAnchor(userInput , 0.1);
        AnchorPane.setBottomAnchor(userInput, 0.1);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Image bgImage = new Image(this.getClass().getResource("/images/background.png").toString());
        Background bg = new Background(new BackgroundImage(
                bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(1.0, 1.0,
                true, true, false, false)));
        mainLayout.setBackground(bg);
        scrollPane.setOpacity(0.9333);
        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        scrollPane.setPrefSize(400, 573);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(345.0);
        userInput.setOnAction((event) -> {
            handleUserInput();
            if (isExit) {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.exit();
                    }
                }, 1000);
            }
        });

        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

    }


    /**
     * Sets up the interface for when the user inputs and also the return string.
     *
     * @param text String that is to be printed on the GUI.
     * @return Label used for printing on the GUI.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


}
