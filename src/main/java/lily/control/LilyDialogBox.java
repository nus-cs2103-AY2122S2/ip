package lily.control;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * This control represents a dialog box consisting of an ImageView to 
 * represent the Lily's face icon and a label containing text from the speaker.
 * 
 * @@author ddx-510 Referenced Dai Tianle for using Region to resize textboxes
 */
public class LilyDialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private int FONT_SIZE = 13;
    @FXML
    private final Font NUNITO_MEDIUM = Font.loadFont(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/fonts/Nunito-Medium.ttf")), FONT_SIZE);

    private final Image LILY_IMAGE = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/lily.png")));

    /**
     * Creates a new dialog box containing what Lily is saying.
     * 
     * @param text that Lily is responding with.
     */
    private LilyDialogBox(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/LilyDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(LILY_IMAGE);
    }

    /**
     * Constructs a dialog box for Lily.
     * 
     * @param text Lily's response.
     * @return a new DialogBox from Lily.
     */
    public static LilyDialogBox getDialog(String text) {
        var db = new LilyDialogBox(text);
        db.setMinHeight(Region.USE_PREF_SIZE);
        return db;
    }
}
