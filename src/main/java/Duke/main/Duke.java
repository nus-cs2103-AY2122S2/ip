package Duke.main;

import Duke.task.TaskList;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Duke extends Application {
    private Storage storage;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public Duke() {}

    /**
     * constructor of the Duck program
     * @param filePath path used to load the file
     * @throws IOException handles input output exceptions
     * @throws ClassNotFoundException handle class not found errors
     */
    public Duke(String filePath) throws ClassNotFoundException, IOException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * this method is used to run the Duke program
     */
    public void run() throws IOException {
        Ui.greeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            Parser p = new Parser(storage, tasks, sc.nextLine());
            p.userCommand();
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
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    String getResponse(String input) {
        ByteArrayOutputStream outPuts = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outPuts);
        PrintStream old = System.out;
        System.setOut(ps);
        try {
            storage = new Storage("duke_Saved.txt");
            tasks = new TaskList(storage.readFromFile());
            Parser p = new Parser(storage, tasks, input);
            p.userCommand();
        } catch (ClassNotFoundException e) {
            Ui.generalErrorMessage();
        } catch (IOException e) {
            tasks = new TaskList();
        }
        System.out.flush();
        System.setOut(old);
        return outPuts.toString();
    }


    /**
     * main method to drive the program
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Duke("duke_Saved.txt").run();
    }
}
