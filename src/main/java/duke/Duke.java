package duke;

import duke.io.Storage;
import duke.parser.Parser;
import duke.task.TaskStore;
import duke.ui.DialogBox;
import duke.ui.MainWindow;
import duke.ui.Ui;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

public class Duke extends Application {
    private TaskStore tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;

//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;


    public Duke() {
//        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
//        this.storage.makeDirectory();
        try {
            this.tasks = storage.importTasks();
        } catch (IOException e) {
            ui.printError("Unable to load file.");
        } catch (DateTimeParseException e) {
            ui.printError("Sorry I don't understand that format. Make sure its in yyyy-mm-dd.");
        }
    }

//    TODO Some serious refactoring needed
    @Override
    public void start(Stage primaryStage) throws Exception {
//        TODO Get rid of this soon
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
//
//        scrollPane.setContent(dialogContainer);
//        scrollPane.setPrefSize(385,535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        userInput = new TextField();
//        userInput.setPrefWidth(325.0);
//
//        sendButton = new Button("Send");
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane mainLayout = new AnchorPane();
//        AnchorPane.setBottomAnchor(sendButton,1.0);
//        AnchorPane.setRightAnchor(sendButton,1.0);
//        AnchorPane.setLeftAnchor(userInput,1.0);
//        AnchorPane.setBottomAnchor(userInput,1.0);
//
//        mainLayout.setPrefSize(400.0,600.0);
//        mainLayout.getChildren().addAll(scrollPane,userInput,sendButton);
//
//        scene = new Scene(mainLayout);
////        TODO can be set outside before running Launcher?
//        primaryStage.setTitle("Duke");
//        primaryStage.setResizable(false);
//
////        Add functionality to handle input
//       sendButton.setOnMouseClicked(event -> handleUserInput());
//
//       userInput.setOnAction(event -> handleUserInput());
//
////        Setting the scene and showing it to user
//        primaryStage.setScene(scene);
//        primaryStage.show();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            this.ui = new Ui(fxmlLoader.<MainWindow>getController().getDialogContainer());
            fxmlLoader.<MainWindow>getController().setParser(this.parser);
            fxmlLoader.<MainWindow>getController().setStorage(this.storage);
            fxmlLoader.<MainWindow>getController().setUi(this.ui);
            fxmlLoader.<MainWindow>getController().setTasks(this.tasks);
            ui.greet();
            primaryStage.setScene(scene);
            primaryStage.setTitle("Duke");
            primaryStage.setMinHeight(600.0);
            primaryStage.setMinWidth(400.0);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void run() {
//        Parser parser = new Parser(ui, storage);
//        Scanner input = new Scanner(System.in);
//
//        ui.greet();
//        String inputTxt;
//        while (input.hasNext()) {
//            inputTxt = input.nextLine();
//            parser.processInput(inputTxt, tasks);
//
//            if (inputTxt.startsWith("bye")) {
//                break;
//            }
//        }
//    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}