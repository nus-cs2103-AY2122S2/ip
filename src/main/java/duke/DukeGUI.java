package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;


public class DukeGUI extends Application {

    //@@author {nus-cs2103-AY2122S2}-reused
    // User images
    private Image user = new Image(this.getClass().getResourceAsStream("/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // Stream for Duke to output to and DukeGUI to read from
    private static ByteArrayOutputStream baos = new ByteArrayOutputStream();
    public static PrintStream outputStream = new PrintStream(baos);

    public static void main() {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }

    @Override
    public void start(Stage stage) {

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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

        //Step 2. Formatting the window to look as expected
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

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput,1.0);

        //Step 3. Add functionality to handle user input.
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
    }

    private void handleUserInput() throws IOException {
        String userInputText = userInput.getText();
        Label userText = new Label(userInputText);
        Label dukeText = new Label(getResponse(userInputText));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }
    //@@author

    private String getResponse(String input) throws IOException {
        // Let Duke read from System.in
        ByteArrayInputStream byteInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteInput);
        Duke.main();

        // Let DukeGUI read from System.out
        String output = DukeGUI.baos.toString();
        DukeGUI.baos = new ByteArrayOutputStream();
        DukeGUI.outputStream = new PrintStream(DukeGUI.baos);

        return output;

    }
}