package duke.gui;

import duke.Duke;
import duke.Exceptions.EmptyMessageException;
import duke.Exceptions.WrongDateFormatException;
import duke.Main;
import duke.UiPrinter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private UiPrinter myUiPrinter = new UiPrinter();

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/user_cat.png")));
    private Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/duke_cat.png")));

    /**
     * Creates a new main window.
     *
     * @param stage the stage of the application
     */
    public MainWindow(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            fxmlLoader.setController(this);
            AnchorPane ap = fxmlLoader.load();
            // fxmlLoader.setRoot(this);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Jarvis");
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(myUiPrinter.printGreeting(), dukeImage)
            );
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws WrongDateFormatException, EmptyMessageException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}