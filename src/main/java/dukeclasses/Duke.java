package dukeclasses;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Represent a program that reads commands from user input and logs them into tasks that are
 * stored for tracking whenever the program is booted up.
 */
public class Duke extends Application {
    private static final String TEXT_DATA_FILE_PATH = "data.txt";
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/Mihile.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/Cygnus.png"));
    private final Image backgroundImage = new Image(
            this.getClass().getResource("/images/background.jpg").toString());

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
            tasks = new TaskList();
        }
    }


    /**
     * Prints user input and duke response.
     *
     * @param output string that represents the output to be printed into the GUI.
     */
    private void updateChatBox(String output) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(output);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(user)));
        DialogBox dukeReply = DialogBox.getDukeDialog(dukeText, new ImageView(duke));
        Timeline delayReply = new Timeline();
        delayReply.getKeyFrames().add(new KeyFrame(Duration.millis(500), (
            event) -> dialogContainer.getChildren().add(dukeReply)));
        delayReply.play();

        userInput.clear();
    }

    /**
     * Executes different commands based on command input and return the string which is the output.
     *
     * @param command command that was parsed from user input.
     * @return output which is the String to be printed  in the GUI as Duke's response.
     */
    private String generateOutput(ParsedInput command) {
        assert command != null : "command should not be null";
        switch (command.getCommand()) {
        case "hi":
            return ui.greet();
        case "bye":
            isExit = true;
            return ui.sayBye();
        case "list":
            return ui.listTask(TEXT_DATA_FILE_PATH);
        case "mark":
        case "unmark":
        case "recur":
            return executeChangeOfStatusCommand(command);
        case "todo":
        case "event":
        case "deadline":
            return executeNewTaskCommand(command);
        case "delete":
            return executeDeleteTaskCommand(command);
        case "find":
            return executeFindCommand(command);
        default:
            return ui.showInputError();
        }
    }

    /**
     * Parses the user input.
     *
     * @return ParsedCommand that represent the user input.
     */
    private ParsedCommand parseUserInput() {
        String stringUserInput = userInput.getText();
        assert stringUserInput != null : "UserInput should not be null";
        try {
            ParsedCommand parsedInput = Parser.parseUserInput(stringUserInput, tasks.getTaskList().size());
            return parsedInput;
        } catch (DukeException errorMessage) {
            return null;
        }
    }

    /**
     * Execute methods related to the find command.
     *
     * @param command command that contains information for find command to execute.
     * @return String output that is printed in the GUI as Duke's response.
     */
    private String executeFindCommand(ParsedInput command) {
        assert command != null : "command should not be null.";
        assert command.getTask() != null : "command Task should not be null.";
        String taskDescription = command.getTask();
        TaskList findTaskList = tasks.findInTaskList(taskDescription);
        return ui.listTaskUsingArrayList(findTaskList);
    }

    /**
     * Execute methods related to the commands that change status of the task.
     *
     * @param command command that contains information for change of status command to execute.
     * @return String output that is printed in the GUI as Duke's response.
     */
    private String executeChangeOfStatusCommand(ParsedInput command) {
        Task modifiedTask;
        if (command.getCommand().equals("mark")) {
            modifiedTask = tasks.updateTask(command.getIndex(), true);
        } else if (command.getCommand().equals("unmark")) {
            modifiedTask = tasks.updateTask(command.getIndex(), false);
        } else if (command.getCommand().equals("recur")) {
            modifiedTask = tasks.recur(command.getIndex());
        } else {
            return ui.showInputError();
        }
        assert modifiedTask != null : "Task should not be null.";

        if (!updateItemsInStorage()) {
            return ui.showStorageError();
        }
        return ui.identifyTask(command, modifiedTask);
    }

    /**
     * Execute methods related to the commands that deletes task.
     *
     * @param command command that contains information for delete command to execute.
     * @return String output that is printed in the GUI as Duke's response.
     */
    private String executeDeleteTaskCommand(ParsedInput command) {
        Task deletedTask;
        try {
            deletedTask = tasks.deleteTask(command.getIndex());
        } catch (DukeException errorMessage) {
            return ui.showInputError();
        }

        if (!updateItemsInStorage()) {
            return ui.showStorageError();
        }

        return ui.deleteTask(deletedTask);
    }

    /**
     * Execute methods related to the commands that creates task.
     *
     * @param command command that contains information for create command to execute.
     * @return String output that is printed in the GUI as Duke's response.
     */
    private String executeNewTaskCommand(ParsedInput command) {
        assert command != null : "command should not be null.";
        assert command.getTask() != null : "command Task should not be null.";
        Task newTask;
        switch (command.getCommand()) {
        case "todo":
            newTask = new ToDo(command.getTask());
            break;
        case "event":
            newTask = new Event(command.getTask(), command.getDueDate(), command.getRecurPeriod());
            break;
        case "deadline":
            newTask = new Deadline(command.getTask(), command.getDueDate(), command.getRecurPeriod());
            break;
        default:
            return ui.showInputError();
        }

        assert newTask != null : "newTask cannot be null";
        tasks.addTask(newTask);

        if (!appendNewTaskToStorage(newTask)) {
            return ui.showStorageError();
        }

        return ui.sayAddTask(newTask, tasks.getTaskList().size());
    }


    /**
     * Contains and runs the main logic of Duke.
     */
    private void handleUserInput() {
        ParsedInput parsedUserInput = parseUserInput();
        if (parsedUserInput == null) {
            updateChatBox(ui.showInputError());
            return;
        }

        String output = generateOutput(parsedUserInput);
        updateChatBox(output);
    }

    /**
     * Adds task to storage file.
     *
     * @param taskToAppend Task that is to be added to storage.
     * @return true if appending succeeds else a false will be returned instead.
     */
    private boolean appendNewTaskToStorage(Task taskToAppend) {
        try {
            storage.appendToStorage(taskToAppend);
        } catch (DukeException errorMessage) {
            return false;
        }
        return true;
    }

    /**
     * Updates storage file.
     *
     * @return true if appending update else a false will be returned instead.
     */
    private boolean updateItemsInStorage() {
        try {
            storage.updateStorageFile(tasks.getTaskList());
        } catch (DukeException errorMessage) {
            updateChatBox(ui.showStorageError());
            return false;
        }
        return true;
    }

    /**
     * Preset settings for the given button
     *
     * @param button The Button object to be used with the settings.
     */
    private void presetSendButton(Button button) {
        AnchorPane.setBottomAnchor(sendButton, 0.1);
        AnchorPane.setRightAnchor(sendButton, 0.1);

        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
    }

    /**
     * Preset settings for the given ScrollPane object.
     *
     * @param pane Pane object to apply settings to.
     * @param box Box object to be applied to pane.
     */
    private void presetScrollPane(ScrollPane pane, VBox box) {
        pane.setPrefSize(400, 573);
        pane.setVvalue(1.0);
        pane.setFitToWidth(true);

        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        AnchorPane.setTopAnchor(scrollPane, 0.1);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setContent(box);
    }

    /**
     * Preset settings for the TextField object.
     *
     * @param input TextField where settings are applied to.
     */
    private void presetUserInput(TextField input) {
        AnchorPane.setLeftAnchor(input , 0.1);
        AnchorPane.setBottomAnchor(input, 0.1);
        input.setPrefWidth(345.0);
        input.setOnAction((event) -> {
            handleUserInput();
            if (isExit) {
                setTimer();
            }
        });
    }

    /**
     * Sets delay for Duke to exit when the bye command is executed.
     */
    private void setTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.exit();
            }
        }, 1500);
    }

    /**
     * Preset settings for the stage given.
     *
     * @param dukeStage Main stage of Duke.
     * @param mainLayoutForStage MainLayout for the main stage of Duke.
     */
    private void presetStage(Stage dukeStage, Scene mainLayoutForStage) {
        dukeStage.setScene(mainLayoutForStage);
        dukeStage.setTitle("Cygnus");
        dukeStage.setResizable(false);
        dukeStage.setMinHeight(600.0);
        dukeStage.setMinWidth(400.0);
    }

    /**
     * Preset settings for the MainLayout class supplied.
     *
     * @param layout The AnchorPane object to apply the settings to
     * @param pane The AnchorPane object to apply to the layout.
     * @param text The TextField object to apply to the layout.
     * @param button The Button object to apply to the layout.
     */
    private void presetMainLayout(AnchorPane layout, ScrollPane pane, TextField text, Button button) {
        layout.setPrefSize(400.0, 600.0);
        layout.getChildren().addAll(pane, text, button);
    }

    /**
     * Preset settings for the background image of Duke's GUI.
     *
     * @param mainLayout The AnchorPane object to apply the background to.
     */
    private void presetBackgroundImage(AnchorPane mainLayout) {
        Background bg = new Background(new BackgroundImage(
                backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(100, 100,
                true, true, false, true)));
        mainLayout.setBackground(bg);
    }

    /**
     * Preset settings for the DialogBox.
     *
     * @param dialogBox The VBox object to apply preset settings to.
     */
    private void presetDialogContainer(VBox dialogBox) {
        dialogBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogBox.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        Label temp = new Label(ui.greet());
        dialogBox.getChildren().add(DialogBox.getUserDialog(
               temp, new ImageView(duke)));
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
        userInput = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);

        presetMainLayout(mainLayout, scrollPane, userInput, sendButton);
        presetBackgroundImage(mainLayout);
        presetScrollPane(scrollPane, dialogContainer);
        presetDialogContainer(dialogContainer);
        presetSendButton(sendButton);
        presetUserInput(userInput);
        presetStage(stage, scene);
        stage.show();


    }

}
