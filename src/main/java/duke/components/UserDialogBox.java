package duke.components;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a user dialog box containing text from the speaker.
 */
public class UserDialogBox extends HBox {
    // File path.
    private static final String DIALOGUE_BOX_FMXL_PATH = "/view/UserDialogBox.fxml";
    private static final String DIALOGUE_BOX_FXML_FILE_NOT_FOUND = "Dialogue Box Fxml file not found";

    @FXML
    private Label dialog;

    private UserDialogBox(String text) {
        try {
            // Checks if DialogueBox.fxml file exists.
            URL dialogueBoxFile = MainWindow.class.getResource(DIALOGUE_BOX_FMXL_PATH);
            assert dialogueBoxFile != null : DIALOGUE_BOX_FXML_FILE_NOT_FOUND;
            // If it exists, continue running.
            FXMLLoader fxmlLoader = new FXMLLoader(dialogueBoxFile);
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
    }

    /**
     * Creates a new dialogue box from the user based on the text.
     * @param text
     * @return
     */
    public static UserDialogBox getUserDialog(String text) {
        return new UserDialogBox(text);
    }
}
