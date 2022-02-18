package duke.components;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * An example of a custom control using FXML.
 * This control represents a response dialog box consisting of an ImageView to represent the responder's face and
 * a label containing text based on the response from the command.
 */
public class ResponseDialogBox extends HBox {
    // File path.
    private static final String DIALOGUE_BOX_FMXL_PATH = "/view/ResponseDialogBox.fxml";
    // Error Messages
    private static final String DIALOGUE_BOX_FMXL_PATH_NOT_FOUND = "Dialogue Box Fxml file not found";
    private static final String IMAGE_NOT_FOUND = "Image not found";

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private ResponseDialogBox(String text, Image img) {
        try {
            // Checks if DialogueBox.fxml file exists.
            URL dialogueBoxFile = MainWindow.class.getResource(DIALOGUE_BOX_FMXL_PATH);
            assert dialogueBoxFile != null : DIALOGUE_BOX_FMXL_PATH_NOT_FOUND;
            assert img != null : IMAGE_NOT_FOUND;
            // If it exists, continue running.
            FXMLLoader fxmlLoader = new FXMLLoader(dialogueBoxFile);
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Creates a new response dialogue box from duke.
     * @param text
     * @param img
     * @return
     */
    public static ResponseDialogBox getDukeDialog(String text, Image img) {
        var db = new ResponseDialogBox(text, img);
        return db;
    }
}
