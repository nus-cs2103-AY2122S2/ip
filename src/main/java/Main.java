
import conan.Conan;
import exceptions.EmptyCommandException;
import exceptions.IllegalCommandException;
import helper.CarryOn;
import helper.DialogBox;
import helper.Ui;
import javafx.application.Application;
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

    // stores empty_string.
    private static final String EMPTY_STRING = "";

    // contains the reference to user image file.
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    // contains the reference to conan image file.
    private final Image conanImage = new Image(this.getClass().getResourceAsStream("/images/conan.png"));

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    /**
     * Starts the user interface.
     *
     * @param stage the window where the program is run.
     */
    @Override
    public void start(Stage stage) {

        // modified from https://se-education.org/guides/tutorials/javaFxPart3.html
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
     * @return appropriate response to the user command.
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
            } else {
                programStage[0] = CarryOn.NEXT;
            }
            return Ui.readFile();

        }

        if (programStage[0] == CarryOn.CONTINUE_FROM_LAST_TIME) {
            if (conan.continueFromLastTime(input)) {
                programStage[0] = CarryOn.NEXT;
            }
            return Ui.readFile();
        }

        if (programStage[0] == CarryOn.NEXT) {

            try {
                if (input.equalsIgnoreCase(EMPTY_STRING)) {
                    throw new EmptyCommandException("");
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
