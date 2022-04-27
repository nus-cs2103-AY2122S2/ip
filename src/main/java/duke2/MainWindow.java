package duke2;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow {
        @FXML
        private ScrollPane scrollPane;
        @FXML
        private VBox dialogContainer;
        @FXML
        private TextField userInput;
        @FXML
        private Button sendButton = new Button("Send");

        private Duke duke;

        private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

        /**
        * Intitalise main window
        */
        @FXML
        public void initialize() {
            String greeting = Ui.printWelcomeMsg();
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(greeting, dukeImage));
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        }

        public void setDuke(Duke d) {
            duke = d;
        }


        @FXML
        private void handleUserInput() throws IOException {
            String input = userInput.getText();
            System.out.println(input);
            String response = duke.getResponse(input);
            System.out.println(response);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            dialogContainer.setSpacing(15);
            userInput.clear();
        }
    }

