package gui;

import chatbots.Fluffers;
import exceptions.SaveFileModifiedException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class encapsulates all the details of the GUI component.
 *
 * @author Ong Han Yang
 */
public class Runner extends Application {
    /** Default border to be reused.*/
    private static final Border DEFAULT_BORDER = new Border(new BorderStroke(
            null, BorderStrokeStyle.SOLID, null, null));
    /** Default inset to be reused. Left and right has insets of 5.*/
    private static final Insets LEFT_RIGHT_INSET = new Insets(0, 5, 0, 5);

    /** The overall container for everything.*/
    private VBox body;
    /** The scene for the overall container({@code body}) to be slotted in.*/
    private Scene scene;
    /** The container for the header of the GUI.*/
    private HBox header;
    /** The container for the footer of the GUI.*/
    private HBox footer;
    /** The container for the main body of the GUI.*/
    private ScrollPane main;
    /** The container for the chatlog of the GUI.*/
    private VBox dialogueContainer;

    /** The user's picture.*/
    private Image userPic;
    /** The GUI component for the user to input commands in.*/
    private TextField userInput;
    /** The GUI component for the user to send commands in.*/
    private Button sendButton;

    /** Fluffer's Profile picture.*/
    private Image fluffersPic;
    /** Fluffer's Profile picture, made smaller to fit the chatlog.*/
    private Image fluffersPicSmall;
    /** Container for Fluffer's Profile picture.*/
    private ImageView fluffersPicContainer;
    /** Label for Fluffers.*/
    private Label fluffersName;
    /** Help Button to configure the GUI.*/
    private Button helpButton;

    /** The Fluffers object.*/
    private Fluffers fluffers;
    /** The file path to the file to load from.*/
    private String filePath;
    /** The file name to load from.*/
    private String fileName;

    /**
     * Runs the GUI after loading all the required components.
     *
     * @param stage the stage to run the GUI on.
     */
    @Override
    public void start(Stage stage) {
        initialiseObjects();
        styleObjects(stage);
        setUpInteractions();
        scene = new Scene(body);
        showStage(stage, scene);

        try {
            startFluffers();
        } catch (SaveFileModifiedException e) {
            System.out.printf("The save file at %s/%s has been modified, and is unreadable."
                    + " Either delete the file, or fix it manually by deleting any wrong entries.%n",
                    filePath, fileName);
            return;
        }
    }

    /**
     * Initialises all the fields/GUI components used in the GUI.
     */
    private void initialiseObjects() {
        body = new VBox();
        header = new HBox();
        main = new ScrollPane();
        footer = new HBox();

        // header
        fluffersPic = new Image(
                "https://i.pinimg.com/originals/22/ab/93/22ab93a52e5e6eb12341bdc1f654988a.jpg",
                50, 50, true, true);
        fluffersPicSmall = new Image(
                fluffersPic.getUrl(),
                35, 35, true, true);
        fluffersPicContainer = new ImageView(fluffersPic);
        fluffersName = new Label("Fluffers");
        helpButton = new Button();
        helpButton.setGraphic(new ImageView(new Image(
                "https://image.flaticon.com/icons/png/512/40/40031.png",
                40, 40, false, true)));
        header.getChildren().addAll(fluffersPicContainer, fluffersName, helpButton);

        // main
        dialogueContainer = new VBox();
        main.setContent(dialogueContainer);

        // footer
        userInput = new TextField();
        sendButton = new Button("Send");
        footer.getChildren().addAll(userInput, sendButton);
        userPic = new Image(
                "https://upload.wikimedia.org/wikipedia/commons/0/09/Man_Silhouette.png",
                35, 35, false, true);

        // finally, add all of them together
        body.getChildren().addAll(header, main, footer);
    }

    /**
     * Styles all the fields/GUI components that is used in the GUI.
     *
     * @param stage the stage to run the GUI on.
     */
    private void styleObjects(Stage stage) {
        stage.setTitle("Cat Translator v2.103.0.3");
        stage.setResizable(false);
        body.setPrefSize(350, 600);
        body.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#91e5ff"), null, null)));

        // header
        fluffersPicContainer.setPreserveRatio(true);
        fluffersPicContainer.setFitHeight(50);
        fluffersName.setPrefSize(350, 50);
        fluffersName.setFont(Font.font(30));
        helpButton.setMinSize(50, 50);
        helpButton.setMaxSize(50, 50);
        helpButton.setVisible(false); // temporarily disable the setting button.
        header.setPadding(new Insets(10));
        HBox.setMargin(fluffersName, LEFT_RIGHT_INSET);

        // main
        dialogueContainer.setPrefHeight(500);
        dialogueContainer.setPrefWidth(335);
        dialogueContainer.setMaxWidth(335);
        dialogueContainer.setPadding(new Insets(5));
        dialogueContainer.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#bdefff"), null, null)));
        main.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        main.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        // footer
        userInput.setPrefSize(300, 35);
        sendButton.setPrefSize(50, 35);
    }

    /**
     * Sets up interactions between the fields/GUI components.
     */
    private void setUpInteractions() {
        sendButton.setOnMouseClicked((event) -> {
            if (userInput.getText().length() == 0) { // Don't do anything if user does not enter anything
                return;
            }
            sendAndReceiveMsg(userInput.getText());
        });

        userInput.setOnAction((event) -> {
            if (userInput.getText().length() == 0) { // Don't do anything if user does not enter anything
                return;
            }
            sendAndReceiveMsg(userInput.getText());
        });

        dialogueContainer.heightProperty().addListener((observable) -> main.setVvalue(1.0));
    }

    /**
     * Shows the stage. This should only be called after initialising everything and setting up
     * interactions beforehand.
     *
     * @param stage the stage to run the GUI on.
     * @param body the scene to be shown on the stage.
     */
    private static void showStage(Stage stage, Scene body) {
        stage.setScene(body);
        stage.show();
    }

    /**
     * Sends a message to Fluffers, and Receives a reply from Fluffers. This method is to provide
     * an encapsulation to group things together.
     *
     * @param input text command from the user.
     */
    private void sendAndReceiveMsg(String input) {
        Node[] userMessage = sendMessageToFluffers(input);
        dialogueContainer.getChildren().addAll(userMessage);
        Node[] receivedMessage = rcvMessageFromFluffers(input);
        userInput.clear();
        dialogueContainer.getChildren().addAll(receivedMessage);
    }

    /**
     * Generates the text message object from the user's input to simulate a message from a messaging app.
     *
     * @param input the user's input
     * @return the nodes that correspond to the user's input, to be slotted into the {@code dialogueContainer}.
     */
    private Node[] sendMessageToFluffers(String input) {
        Label textToAdd = generateLabel(input);
        HBox messageAndPic = new HBox();
        messageAndPic.getChildren().addAll(textToAdd, new ImageView(userPic));

        AnchorPane anchorPane = new AnchorPane(messageAndPic);
        AnchorPane.setRightAnchor(messageAndPic, 1.0);
        anchorPane.setMaxWidth(330);

        return new Node[]{anchorPane, generateBlankLine()};
    }

    /**
     * Sends a message to Fluffers, receiving an appropriate response accordingly.
     *
     * @param input text command from the user.
     * @return the response from Fluffers, to be slotted into the {@code dialogueContainer}.
     */
    private Node[] rcvMessageFromFluffers(String input) {
        Label textToAdd = generateLabel(fluffers.getReply(input));
        HBox messageAndPic = new HBox();
        messageAndPic.getChildren().addAll(new ImageView(fluffersPicSmall), textToAdd);

        AnchorPane anchorPane = new AnchorPane(messageAndPic);
        AnchorPane.setLeftAnchor(messageAndPic, 5.0);
        anchorPane.setMaxWidth(330);

        return new Node[]{anchorPane, generateBlankLine()};
    }

    /**
     * Generates a label for the conversation with appropriate styling.
     *
     * @param input the text to be included in the label.
     * @return the label, after including the styling and the String input.
     */
    private Label generateLabel(String input) {
        Label textToAdd = new Label(input);
        textToAdd.setWrapText(true);
        textToAdd.setMaxWidth(249);
        textToAdd.setBorder(DEFAULT_BORDER);
        textToAdd.setPadding(LEFT_RIGHT_INSET);
        textToAdd.setBackground(new Background(new BackgroundFill(
                Color.valueOf("#f7f7f7"), null, null)));
        return textToAdd;
    }

    /**
     * Generates a blank line to be used as a spacer in the {@code dialogueContainer}.
     *
     * @return a blank line node of height 5.
     */
    private Node generateBlankLine() {
        VBox box = new VBox();
        box.setMinHeight(5);
        return box;
    }

    /**
     * Loads the file path and file name into Fluffers, and wakes her up.
     *
     * @throws SaveFileModifiedException when the save file contains invalid symbols that could
     *          not have been from the string representation of a task, indicating external
     *          modification to the file.
     */
    private void startFluffers() throws SaveFileModifiedException {
        this.filePath = "data";
        this.fileName = "saved-taskList.txt";
        this.fluffers = Fluffers.of(filePath, fileName);
        fluffers.wakeUp();
    }
}
