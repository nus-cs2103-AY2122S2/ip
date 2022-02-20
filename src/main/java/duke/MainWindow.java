package duke;

import static javafx.application.Platform.exit;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaHog.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaCat.png"));

    /**
     * Method that starts DukeLCH in the GUI
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox
                .getDukeDialog("_______________________________________________________\n"
                        + " ____         _           _     ____ _   _ \n"
                        + "|  _ \\ _   _| | _____   | |  /  ___| | | |\n"
                        + "| | | |  | | | |/ / _ \\ | | |  |   | |_| |\n"
                        + "| |_| |  |_| |  <   __/  | |_|  |___|  _  |\n"
                        + "|____/ \\__,_|_|\\_\\___||___|\\____|_| |_|\n"
                        + "Hello! I'm DukeLCH\n"
                        + "How can I be of service?\n" //Simple Greet
                        + "_______________________________________________________\n", dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = duke.run(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            ScheduledExecutorService exit = Executors.newSingleThreadScheduledExecutor();
            exit.schedule(MainWindow::endDuke, 1, TimeUnit.SECONDS);
        }
    }

    private static void endDuke() {
        System.exit(0);
    }
}
