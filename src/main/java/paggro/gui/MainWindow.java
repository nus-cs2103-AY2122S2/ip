package paggro.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String WELCOME_MESSAGE = "    What do you want... =.=";
    private static final String WINDOW_CLOSING = "    Window closing in 3 seconds...";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private PaggroBot paggro;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image paggroImage = new Image(this.getClass().getResourceAsStream("/images/unamused.png"));

    /**
     * Initializes the MainWindow controller.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        Background paggroBackground = new Background(new BackgroundFill(
                Color.DARKGREY, new CornerRadii(20), new Insets(8, 8, 8, 8)));
        setBackground(paggroBackground);
    }

    public void setPaggro(PaggroBot p) {
        paggro = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String paggroText = paggro.getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getPaggroDialog(paggroText, paggroImage)
        );
        userInput.clear();
        if (paggro.isExit) {
            dialogContainer.getChildren().addAll(DialogBox.getPaggroDialog(WINDOW_CLOSING, paggroImage));

            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Platform.exit();
                System.exit(0);
            }).start();
        }
    }

    /**
     * Displays welcome message.
     */
    @FXML
    void greet() {
        dialogContainer.getChildren().addAll(
                DialogBox.getPaggroDialog(WELCOME_MESSAGE, paggroImage)
        );
    }
}
