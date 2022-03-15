package duke.ui;

import duke.Duke;
import duke.commands.ByeCommand;
import duke.commands.parser.Parser;
import duke.tasks.TaskList;
import duke.commands.Command;
import duke.exceptions.DukeException;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class Ui extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private String welcome = "Welcome to Duke! What can I do for you today? \n";
    private Label label = new Label(welcome);
    private Parser parser = new Parser();
    private TaskList taskList = Duke.tasks;


    @Override
    public void start(Stage stage) {
        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, label);

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

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // more code to be added here later
    }

    private void handleUserInput() {

        try {
            this.label.setVisible(false);
            String userText = userInput.getText();
            String response = "";


            Command command = parser.parse(userText);

            if (command instanceof ByeCommand) {
                Platform.exit();
            }
            response = command.execute(taskList);

            Label userTextLabel = new Label(userText);
            Label dukeTextLabel = new Label(response);
            dialogContainer.getChildren().addAll(
                    new DialogBox(userTextLabel, new ImageView(user)),
                    new DialogBox(dukeTextLabel, new ImageView(duke)).flip()
            );
            userInput.clear();
        } catch (DukeException e) {
            dialogContainer.getChildren().add(
                    new DialogBox(e.getLabel(), new ImageView(duke)).flip()
            );
            userInput.clear();
        }

        /*
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
         */
    }



}
