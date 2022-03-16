
package juke;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Juke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private ArrayList<Task> itemList = new ArrayList<>();


    private Image user = new Image(this.getClass().getResourceAsStream("/images/mumen.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/botak.png"));

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }


    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     *
     * @param input
     * @return Juke's response based on the command
     */
    private String getResponse(String input) {

        Outputs op = new Outputs();

        while(true) {
            String reply = input;
            String[] splittedString = input.split(" ");

            File file = new File("data/duke.txt");
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) { }
            if (Tag.isTag(splittedString)) { // check for mark tag
                return Tag.addTag(itemList, splittedString, reply, op);
            } else if (Mark.isMark(splittedString)) { // check for mark tag
                return Mark.addMark(itemList, reply, op);
            } else if (Unmark.isUnmark(splittedString)) { // check for unmark tag
                return Unmark.addUnmark(itemList, reply, op);
            } else if (Delete.isDelete(splittedString)) { //check for delete
                return Delete.executeDelete(itemList, splittedString, op);
            } else if (Todo.isTodo(splittedString)) { // check for todo tag
                return Todo.executeTodo(itemList, splittedString, reply, op);
            } else if (Deadline.isDeadline(splittedString)) { // check for deadline tag
                return Deadline.executeDeadline(itemList, splittedString, reply, op);
            } else if (Event.isEvent(splittedString)) { // check for event tag
                return Event.executeEvent(itemList, splittedString, reply, op);
            } else if (reply.equals("list")) { // check for list
                return Generalcommands.executeList(itemList, op);
            } else if (splittedString[0].equals("find")) {
                return Generalcommands.executeFind(itemList, splittedString, op);
            } else if (splittedString[0].equals("help")){
                return op.help;
            } else if (reply.equals("bye")) { // check for bye
                System.exit(0);
            } else { //check non-existing commands
                return op.border +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        op.instructions +
                        op.border;
            }
            String file2 = "data/duke.txt";
            try {
                for (Task t : itemList) {
                    writeToFile(file2, t.getDescription() + "\n");
                }
            } catch (IOException e) {
                System.out.println("");
            }


        }
    }




    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        sendButton.setStyle("fx-font-family: Lucida Sans Unicode;" +
                "-fx-font-size: 20;" +
                "-fx-text-fill: #FF69B4;" +
                "-fx-background-color: #89cff0 ; " +
                "-fx-background-radius: 10");
        sendButton.setTranslateX(-5);

        BackgroundFill myBF = new BackgroundFill(Color.HONEYDEW, new CornerRadii(1),
                new Insets(0.0,0.0,0.0,0.0));// or null for the padding
        // then you set to your node or container or layout
        dialogContainer.setBackground(new Background(myBF));
        dialogContainer.setStyle("fx-font-family: Lucida Sans Unicode;" +
                "-fx-font-size: 15;" +
                "-fx-text-fill: #FF69B4;" +
                "-fx-background-color: pink ; " +
                "-fx-background-radius: 30");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        scene.setFill(Color.AQUAMARINE);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Juke");
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

        userInput.setPrefSize(280, 50);
        userInput.setTranslateX(10);
        userInput.setStyle(
                "-fx-font-family: Comic Sans MS;" +
                "-fx-font-size: 20;" +
                "-fx-text-fill: #a87bab;" +
                "-fx-background-color: #89cff0 ; " +
                "-fx-background-radius: 10");

        sendButton.setPrefSize(100,50);
        sendButton.setBorder(
                new Border(
                new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(3))));
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //event handler
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }


}
