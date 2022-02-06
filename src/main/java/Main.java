
import java.util.Scanner;

import Exceptions.EmptyCommandException;
import Exceptions.IllegalCommandException;

import Helper.CarryOn;
import Helper.Ui;

import Conan.Conan;

import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * <h1>Main</h1>
 * <p>
 * Main class serves as a mediator class between the user and Conan.
 * </p>
 *
 * @author Saravanan Anuja Harish
 */
public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image conanImage = new Image(this.getClass().getResourceAsStream("/images/conan.png"));

    private final static String EMPTY_STRING = "";

    /**
     * starts the program.
     *
     * @param args parameter to start the main method.
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // creates a new instance of conan.

        Conan conan = new Conan();
        CarryOn carryOn = CarryOn.NEXT;
        String userInput = sc.nextLine();

        //keep asking the user for name till u get a valid name.
        while (userInput.trim().equalsIgnoreCase(EMPTY_STRING)) {
           // Ui.printSeparator();
            Ui.printAskValidName();
            userInput = sc.nextLine();
        }

        // code when a similar user is found.
        boolean isSimilarPreviousUser = conan.tellName(userInput);
        if (isSimilarPreviousUser) {
            userInput = sc.nextLine();
            while(!conan.continueFromLastTime(userInput)) {
                userInput = sc.nextLine();
            }
        }

        // while the user still wants to type in.
        while (carryOn == CarryOn.NEXT) {

            try {
                // get the text from the user.
                String text = sc.nextLine();
                // tell conan the text and update if the user wants to continue.
                if (text.equalsIgnoreCase(EMPTY_STRING)) {
                    throw new EmptyCommandException(EMPTY_STRING);
                }
                carryOn = conan.tell(text);
            } catch (IllegalCommandException e) {
                Ui.printMessage(e.toString());
                //Ui.printSeparator();
            }
        }
    }

    /**
     * Starts the user interface.
     *
     * @param stage the window where the program is run.
     */
    @Override
    public void start(Stage stage) {

        // Step 1 : the container for the content of the chat to scroll;
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        scene.setFill(Color.web("#4378c0"));

        stage.setScene(scene);
        stage.show(); // show the stage.

        // step 2 : Formatting the window to look as expected.
        stage.setTitle("Conan");
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        ///////////////////////////////////////////////////////////////////////

        // start conan print greetings
        Conan conan = new Conan();
        CarryOn[] programStage = {CarryOn.USERNAME};
        String str = Ui.readFile();

        Label greetingText = new Label(str);
        dialogContainer.getChildren().addAll(
                DialogBox.getConanDialog(greetingText, new ImageView(conanImage))
        );

        //////////////////////////////////////////////////////////////////
        // step 3: Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(programStage, conan);
        });

        userInput.setOnAction((event) -> {
            handleUserInput(programStage, conan);
        });

    }

    /**
     * handles the user input.
     *
     * @param programStage the stage in which the program is currently in.
     * @param conan the task manager instance.
     */
    private void handleUserInput(CarryOn[] programStage, Conan conan) {
        Label userText = new Label(userInput.getText());
        Label conanText = new Label(getResponse(userInput.getText(), programStage, conan));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getConanDialog(conanText, new ImageView(conanImage))
        );
        userInput.clear();
    }

    /**
     * gets an appropriate response to the user input.
     *
     * @param input the input given by the user.
     * @param programStage the stage in which the program is currently in.
     * @param conan the task manager instance.
     * @return
     */
    private String getResponse(String input, CarryOn[] programStage, Conan conan) {



        if (programStage[0] == CarryOn.USERNAME) {
            if (input.equalsIgnoreCase(EMPTY_STRING)) {
                Ui.printAskValidName();
                return Ui.readFile();
            }

            boolean isSimilarPreviousUser = conan.tellName(input);
            if (isSimilarPreviousUser) {
                programStage[0] = CarryOn.CONTINUE_FROM_LAST_TIME;
                return Ui.readFile();
            } else {
                programStage[0] = CarryOn.NEXT;
                return Ui.readFile();
            }

        }

        if (programStage[0] == CarryOn.CONTINUE_FROM_LAST_TIME) {
            if (conan.continueFromLastTime(input)) {
                programStage[0] = CarryOn.NEXT;
                return Ui.readFile();
            } else {
                return Ui.readFile();
            }
        }

        if (programStage[0] == CarryOn.NEXT) {

            try {
                if (input.equalsIgnoreCase(EMPTY_STRING)) {
                    throw new RuntimeException();
                }
                programStage[0] = conan.tell(input);
                return Ui.readFile();
            } catch (IllegalCommandException e) {
                Ui.printMessage(e.toString());
                return Ui.readFile();
            }

        }
        return EMPTY_STRING;
    }
}
