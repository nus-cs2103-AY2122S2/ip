package juke;

import java.util.ArrayList;

import javafx.geometry.Insets;
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
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UI {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/mumen.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/botak.png"));

    private void handleUserInput(ArrayList<Task> arrList) {
        try {
//            System.out.println(Parser.taskToString(arrList.get(0)));
        } catch (Exception e) {
//            System.out.println("nothing");
        }
        Parser p = new Parser();
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(p.getResponse(userInput.getText(), arrList));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }


    public void layout(Stage stage, ArrayList<Task> arrList) {
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
                new Insets(0.0, 0.0, 0.0, 0.0));// or null for the padding
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

        sendButton.setPrefSize(100, 50);
        sendButton.setBorder(
                new Border(
                        new BorderStroke(Color.BLUEVIOLET, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(3))));
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //event handler
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(arrList);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(arrList);
        });
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }


}
