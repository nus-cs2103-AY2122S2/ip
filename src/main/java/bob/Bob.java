package bob;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.ListCommand;
import bob.exception.BobException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Bob class implements a chat-bot that records your tasks and tracks your progress.
 *
 * @author Ryan Low
 * @version 1.0
 */
public class Bob extends Application {
    private Storage store;
    private Ui ui;
    private TaskList tasks;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image bobPic = new Image(this.getClass().getResourceAsStream("/images/bobpfp.jpeg"));
    private Image userPic = new Image(this.getClass().getResourceAsStream("/images/userpfp.jpeg"));
    public Bob() {

    }

    public Storage getStore() {
        return store;
    }

    public Ui getUi() {
        return ui;
    }

    /**
     * Represents the initializer of the Bob program.
     * Sets up the store, tasks, and ui for the program.
     */
    public void initializeBob() {
        store = new Storage("Bob.txt");
        tasks = new TaskList(store.getSavedStore());
        ui = new Ui();
        greet();
        activeListener();
    }

    /**
     * Prints the greeting messages for Bob upon initializing.
     */
    public void greet() {
        StringBuilder greetString = new StringBuilder();
        greetString.append(ui.greetMessage());
        greetString.append("\n");
        greetString.append(new ListCommand(true).execute(tasks, ui, store));
        dialogContainer.getChildren().addAll(DialogBox.getBobDialog(greetString.toString(), bobPic));
    }

    /**
     * Runs the program by actively listening to commands and executes them.
     */
    public void activeListener() {
        boolean isBye = false;
        while (!isBye) {
            String input = ui.readInput();
            if (input.isBlank()) {
                ui.badReply();
            } else {
                try {
                    Command c = Parser.parse(input);
                    isBye = c instanceof ByeCommand;
                    c.execute(tasks, ui, store);
                } catch (BobException e) {
                    ui.showError(e.getBobReply());
                }
            }
            if (!isBye) {
                ui.userReply();
            }
        }
    }

    /**
     * Creates the nodes required for the chat box.
     * @return a Vbox containing the nodes for the chat box
     */
    public VBox createChatBox() {
        HBox smallLayout = new HBox();
        smallLayout.getChildren().addAll(userInput, sendButton);
        VBox mainLayout = new VBox();
        mainLayout.getChildren().addAll(scrollPane, smallLayout);
        mainLayout.setPrefSize(400.0, 600.0);
        return mainLayout;
    }

    private static void setSceneStyle(Scene scene) {
        scene.getRoot().setStyle("-fx-font-family: 'Courier New'; "
                + "-fx-font-size: 15; "
                + "-fx-background-color: GREY");
    }

    private static void setStage(Stage stage) {
        stage.setTitle("Bob");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(600.0);
    }

    private void setScrollPaneStyle(ScrollPane scrollPane) {
        scrollPane.setPrefSize(385, 570);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void setAnchor(ScrollPane scrollPane, TextField userInput, Button sendButton) {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private static void setDialogStyle(VBox dialogContainer) {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(25);
    }
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        store = new Storage("Bob.txt");
        tasks = new TaskList(store.getSavedStore());
        ui = new Ui();

        var mainLayout = createChatBox();

        scene = new Scene(mainLayout);
        setSceneStyle(scene);
        setStage(stage);
        setScrollPaneStyle(scrollPane);
        setDialogStyle(dialogContainer);
        // You will need to import `javafx.scene.layout.Region` for this.
        userInput.setPrefWidth(500.0);
        sendButton.setPrefWidth(100.0);
        setAnchor(scrollPane, userInput, sendButton);
        stage.setScene(scene);
        stage.show();
        greet();
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        if (userInput.getText().trim().isBlank()) {
            userInput.setStyle("-fx-border-color: red; -fx-border-width: 1.5;");
        } else {
            userInput.setStyle("-fx-border-width: 0");
            String userText = "You: " + userInput.getText();
            try {
                String bobText = getResponse(userInput.getText());
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(userText, userPic),
                        DialogBox.getBobDialog(bobText, bobPic)
                );
                userInput.clear();
            } catch (BobException e) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(userText, userPic),
                        DialogBox.getErrorDialog(e.getBobReply(), bobPic)
                );
                userInput.clear();
            }
        }
    }

    public String getResponse(String input) throws BobException {
        ByteArrayOutputStream bobReply = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bobReply));
        Command c = Parser.parse(input);
        if (c instanceof ByeCommand) {
            Platform.exit();
        }
        return c.execute(tasks, ui, store);
    }
}
