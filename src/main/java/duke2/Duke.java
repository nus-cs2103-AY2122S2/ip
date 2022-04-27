package duke2;// package duke1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.FileNotFoundException;
//import javafx.scene.control.Label;

public class Duke extends Application {

    private Storage storage;
    protected static Tasklist tasks = new Tasklist();
    private Ui ui;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    public static int totalCount = 0;

    /**
     * Constructor for Duke
     *
     * @param path
     * @returns new Duke object
     */

    /*
    public Duke(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new Tasklist(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
            tasks = new Tasklist();
        }
    }
     */

    /**
     * Constructor for Duke
     *
     * @returns new Duke object
     */

    /*
    public Duke() {

    }

     */

    @Override
    public void start(Stage stage) throws IOException {
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

        stage.setTitle("DukeGenie");
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

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * To run Duke UI
     */
    /*
    public void run() {

        ui.printWelcomeMsg();
        System.out.println(this.storage.getPath());
        Scanner s = new Scanner(System.in);
        String[] input = s.nextLine().split(" ");
        System.out.println(input);
        while (!(input[0].toLowerCase().equals("bye"))) {
            String command = input[0].toLowerCase();
            Parser p = new Parser();
            String str = p.commandToTask(command, this.tasks.getTasks(), input, this.storage.getPath());
            input = s.nextLine().split(" ");
        }
        ui.printEndMsg();
    }
     */

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() throws IOException {
        String userText = userInput.getText();
        String dukeText = getResponse("test");
        //String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }

    /**
     * Get response from other duke
     *
     * @param input for response
     * @returns response to be echoed
     */
    public String getResponse(String input) throws IOException {

        if (input.equals("bye")) {
            return ui.printEndMsg();
        } else {
            if (this.tasks.isEmpty()) {
                Storage.createFiles();
            }
            Parser p = new Parser();
            String[] arr = input.split(" ");
            String str = p.commandToTask(arr[0], this.tasks.getTasks(), arr, this.storage.getPath());
            return str;
        }
    }

    /*
    public static void main(String[] args) throws IOException {

        String currDir = System.getProperty("user.dir");
        String filePath = "src/data/duke.txt";
        System.out.println(filePath);
        File f = new File(currDir+"/duke.txt");
        System.out.println(f.createNewFile());
        if (f.createNewFile()) {
            Path dp = Paths.get("/src/data/");
            Files.createDirectories(dp);
            System.out.println(dp);
            Path p = Paths.get(currDir+"/src/data/duke.txt");
            System.out.println(p);
            Files.createFile(p);
            new Duke(currDir+"/src/data/duke.txt").run();
        } else {
            System.out.println(filePath);
            new Duke(filePath).run();
        }

    }
     */
}