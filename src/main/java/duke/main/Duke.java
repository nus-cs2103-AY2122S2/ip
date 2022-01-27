package duke.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javafx.application.Application;
import javafx.geometry.Pos;
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
 * The main class for running Duke.
 * This class contains all the abstracted details for Duke.
 */
public class Duke extends Application {
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private TaskList toDoList;
    private Storage storage;
    private Ui ui;

    private final String filepath = "./tasklist.txt";
    private boolean isFirstStartup = true;
    /**
     * Constructor for Duke.
     * Tries to retrieve the list of Tasks from the default filepath
     */
    public Duke() {
        storage = new Storage(this.filepath);
        ui = new Ui();

        // Attempt to open the storage file. If file does not exist, then create a new file.
        try {
            toDoList = storage.addFileContent();
        } catch (FileNotFoundException e) {
            Ui.handleLoadError();
            // creates a new file in the current directory
            File f = new File(this.filepath);
            toDoList = new TaskList();
        }
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
        stage.setTitle("Burp");
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
        AnchorPane.setLeftAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            this.handleUserInput();
        });

        userInput.setOnAction((event) -> {
            this.handleUserInput();
        });

        // Call this on startup to show welcome text
        if (isFirstStartup) {
            this.onStartUp();
            this.isFirstStartup = false;
        }

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    @Override
    public void stop() {
        try {
            Storage.writeFileContent(toDoList);
        } catch (IOException e) {
        }
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label("\n"+userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        userText.setWrapText(true);
        dukeText.setWrapText(true);

        DialogBox userBox = DialogBox.getUserDialog(userText, new ImageView(user));
        DialogBox dukeBox = DialogBox.getDukeDialog(dukeText, new ImageView(duke));

        dialogContainer.getChildren().addAll(
                userBox,
                dukeBox
        );

        dialogContainer.setAlignment(Pos.CENTER_LEFT);

        userInput.clear();
    }
    private void onStartUp() {
        Ui.showWelcome();
        Label dukeText = new Label(Ui.getDukeResponse());

        DialogBox dukeWelcomeBox = DialogBox.getDukeDialog(dukeText, new ImageView(duke));
        dialogContainer.getChildren().addAll(
                dukeWelcomeBox
        );
    }
    /**
     * Gets Burp's response in return to a command given to it.
     *
     * @param input the user input
     * @return Burp's response
     */
    private String getResponse(String input) {
        try {
            String commandType = input.split(" ")[0];
            ui.burpReply(ui.determineType(commandType), toDoList, input, this.storage);
            return Ui.getDukeResponse();
        } catch (DukeException e) {
            return Ui.getDukeResponseError();
        }
    }

    /**
     * Driver for Duke.
     * Runs Duke and awaits for Commands from the user.
     *
     * @throws DukeException when a WrongCommand is given
     * @throws IOException   when an IO error occurs while reading user input
     */
    public void run() throws DukeException, IOException {
        // Declaration of variables
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd;

        // Print out the welcome message and await user input
        Ui.showWelcome();
        while (!(cmd = br.readLine()).equals("bye")) {
            String commandType = cmd.split(" ")[0];
            ui.burpReply(ui.determineType(commandType), toDoList, cmd, this.storage);
        }
        Ui.showBye();
    }

    /**
     * Main method. Used for CLI programs
     *
     * @param args unused
     * @throws DukeException when a WrongCommand is given
     * @throws IOException   when an IO error occurs
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke().run();
    }
}
