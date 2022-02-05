package duke.main;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.dukeexceptions.DukeException;
import duke.parser.Parser;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;

public class Duke {
    //public class Duke {
    /**
     * A task taking chatbot.
     *
     */
    private final Storage storage;
    private Ui ui;
    private TaskList taskList;
    //    private VBox dialogContainer;
    //    private TextField userInput;
    //    private Button sendButton;
    //    private Scene scene;
    //    private ScrollPane scrollPane;


    /**
     * Constructor for Duke.
     * @param filePath the data file
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("No path exist");
            taskList = new TaskList();
        }
    }

    /**
     * Main method to activate chatbot
     *
     * @throws IOException error when saving to the data file
     */
    public void run() throws IOException {
        ui = new Ui();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = sc.nextLine();
                ui.showLine();
                Command c = Parser.parseCommand(input);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showEndline();
            }


        }
    }

    public String getResponse(String input) throws DukeException {
        try {
            Command c = Parser.parseCommand(input);
            c.execute(taskList, ui, storage);
            if (c.isExit()) {
                return "exit";
            }
            return "hihi";
        } catch (DukeException | IOException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    //    /*
    //    /**
    //     * The main entry point for all JavaFX applications.
    //     * The start method is called after the init method has returned,
    //     * and after the system is ready for the application to begin running.
    //     *
    //     * <p>
    //     * NOTE: This method is called on the JavaFX Application Thread.
    //     * </p>
    //     *
    //     * @param stage the primary stage for this application, onto which
    //     *                     the application scene can be set.
    //     *                     Applications may create other stages, if needed, but they will not be
    //     *                     primary stages.
    //     * @throws Exception if something goes wrong
    //     */
    //    @Override
    //    public void start(Stage stage) throws Exception {
    //        //Step 1. Setting up required components
    //        //The container for the content of the chat to scroll.
    //        scrollPane = new ScrollPane();
    //        dialogContainer = new VBox();
    //        scrollPane.setContent(dialogContainer);
    //
    //        userInput = new TextField();
    //        sendButton = new Button("Send");
    //
    //        AnchorPane mainLayout = new AnchorPane();
    //        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
    //
    //        scene = new Scene(mainLayout);
    //
    //        stage.setScene(scene);
    //        stage.show();
    //
    //        //Step 2. Formatting the window to look as expected
    //        stage.setTitle("Duke");
    //        stage.setResizable(false);
    //        stage.setMinHeight(600.0);
    //        stage.setMinWidth(400.0);
    //
    //        mainLayout.setPrefSize(400.0, 600.0);
    //
    //        scrollPane.setPrefSize(385, 535);
    //        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    //        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    //
    //        scrollPane.setVvalue(1.0);
    //        scrollPane.setFitToWidth(true);
    //
    //        // You will need to import `javafx.scene.layout.Region` for this.
    //        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    //
    //        userInput.setPrefWidth(325.0);
    //
    //        sendButton.setPrefWidth(55.0);
    //
    //        AnchorPane.setTopAnchor(scrollPane, 1.0);
    //
    //        AnchorPane.setBottomAnchor(sendButton, 1.0);
    //        AnchorPane.setRightAnchor(sendButton, 1.0);
    //
    //        AnchorPane.setLeftAnchor(userInput , 1.0);
    //        AnchorPane.setBottomAnchor(userInput, 1.0);
    //    }
    //    */

}
