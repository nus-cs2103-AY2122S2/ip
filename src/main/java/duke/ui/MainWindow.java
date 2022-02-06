package duke.ui;

import java.io.IOException;

import duke.main.Duke;
import duke.utils.Pair;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String LOADING_ERROR_MESSAGE = "Unable to read saved task from file.\n"
            + "Starting with a new task list..";
    private static final String CLOSING_MESSAGE = "Closing this app in 3 seconds..";
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke = new Duke();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Ash.PNG"));

    /**
     * Initializes a new MainWindow and sets itself to be the root and controller of MainWindow fxml.
     */
    public MainWindow() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.getStylesheets().add("/view/mainWindow.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        initializeBackgrounds();
    }

    @FXML
    private void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; ");
        String welcomeMessage = duke.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, Ui.GENERAL_IMAGE)
        );
        if (duke.hasLoadingError()) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(LOADING_ERROR_MESSAGE, Ui.GENERAL_IMAGE)
            );
            duke.setHasLoadingError(false);
        }
    }

    private void initializeBackgrounds() {
        //set main background
        Image fireImage1 = new Image(this.getClass().getResourceAsStream("/images/Background.jpg"));
        BackgroundImage mainBackgroundImage = new BackgroundImage(fireImage1,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, true));
        Background background = new Background(mainBackgroundImage);
        this.setBackground(background);

        //set input background
        Image fireImage2 = new Image(this.getClass().getResourceAsStream("/images/FireBackground.jpg"));
        BackgroundImage inputBackgroundImage = new BackgroundImage(fireImage2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, true));
        Background inputBackground = new Background(inputBackgroundImage);
        userInput.setBackground(inputBackground);
        sendButton.setBackground(inputBackground);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (duke.hasExited()) {
            prepareToCloseWindow();
        }
        String input = userInput.getText();
        Pair<String, Image> dukeResponse = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(dukeResponse.getFirst(), dukeResponse.getSecond())
        );
        userInput.clear();
    }

    private void prepareToCloseWindow() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(CLOSING_MESSAGE, Ui.GENERAL_IMAGE)
        );
        sendButton.setDisable(true);
        userInput.setDisable(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> javafx.application.Platform.exit());
        delay.play();
    }
}
