package dukeclasses;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
 * Represents the graphical user interface, for users to view and interact with the program.
 */
public class Gui {
    private Duke duke;
    //Image Credits: taken from https://maplestory.fandom.com/wiki/Mihile/Job
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/Mihile.png"));
    //Image Credits: taken from https://maplestory.fandom.com/wiki/Cygnus
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Cygnus.png"));
    //Image Credits: taken from https://www.space.com/26552-a-deep-look-space-wallpaper.html
    private final Image backgroundImage = new Image(
        this.getClass().getResource("/images/background.jpg").toString());
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;
    private OutputString outputString;

    private Gui(Duke duke, Stage stage) {
        sendButton = new Button("Send");
        dialogContainer = new VBox();
        userInput = new TextField();
        scrollPane = new ScrollPane();
        mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);
        outputString = new OutputString();
        this.duke = duke;
    }

    /**
     * Presets GUI for Duke.
     *
     * @param duke The program that uses the GUI created.
     * @param dukeStage Stage for the GUI.
     * @return GUI with the preset settings to be used by the program Duke.
     */
    public static Gui createGuiForDuke(Duke duke, Stage dukeStage) {
        Gui gui = new Gui(duke, dukeStage);
        gui.presetSendButton();
        gui.presetBackgroundImage();
        gui.presetDialogContainer();
        gui.presetMainLayout();
        gui.presetScrollPane();
        gui.presetUserInput();
        gui.presetStage(dukeStage);
        return gui;
    }


    /**
     * Preset settings for the given ScrollPane object.
     */
    private void presetScrollPane() {
        scrollPane.setPrefSize(400, 573);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        AnchorPane.setTopAnchor(this.scrollPane, 0.1);
        this.scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        this.scrollPane.setContent(dialogContainer);
    }

    /**
     * Preset settings for the TextField object.
     */
    private void presetUserInput() {
        AnchorPane.setLeftAnchor(userInput , 0.1);
        AnchorPane.setBottomAnchor(userInput, 0.1);
        userInput.setPrefWidth(345.0);
        userInput.setOnAction((event) -> {
            duke.handleUserInput(getUserInput());
            if (duke.getExitStatus()) {
                setTimer();
            }
        });
    }


    /**
     * Preset settings for the MainLayout class supplied.
     */
    private void presetMainLayout() {
        mainLayout.setPrefSize(400.0, 600.0);
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
    }


    /**
     * Preset settings for the background image of Duke's GUI.
     */
    private void presetBackgroundImage() {
        Background bg = new Background(new BackgroundImage(
            backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.DEFAULT, new BackgroundSize(100, 100,
            true, true, false, true)));
        mainLayout.setBackground(bg);
    }

    /**
     * Preset settings for the DialogBox.
     */
    private void presetDialogContainer() {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        Label temp = new Label(outputString.greet());
        dialogContainer.getChildren().add(DialogBox.getUserDialog(
            temp, new ImageView(dukeImage)));
    }

    /**
     * Preset settings for the given button
     */
    private void presetSendButton() {
        AnchorPane.setBottomAnchor(sendButton, 0.1);
        AnchorPane.setRightAnchor(sendButton, 0.1);

        sendButton.setPrefWidth(55.0);
        sendButton.setOnMouseClicked((event) -> {
            duke.handleUserInput(getUserInput());
        });
    }

    /**
     * Contains and runs the main logic of Duke.
     */
    private String getUserInput() {
        return userInput.getText();
    }

    /**
     * Prints user input and duke response.
     *
     * @param output string that represents the output to be printed into the GUI.
     */
    public void updateChatBox(String output) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(output);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(userText, new ImageView(userImage)));
        DialogBox dukeReply = DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage));
        Timeline delayReply = new Timeline();
        delayReply.getKeyFrames().add(new KeyFrame(Duration.millis(500), (
            event) -> dialogContainer.getChildren().add(dukeReply)));
        delayReply.play();

        userInput.clear();
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
     */
    private void presetStage(Stage dukeStage) {
        dukeStage.setScene(scene);
        dukeStage.setTitle("Cygnus");
        dukeStage.setResizable(false);
        dukeStage.setMinHeight(600.0);
        dukeStage.setMinWidth(400.0);
    }
}
