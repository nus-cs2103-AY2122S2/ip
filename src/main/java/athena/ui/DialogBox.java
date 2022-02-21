package athena.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final String ATHENA_DIALOG_COLOR = "dae8ff";
    private static final String ATHENA_ERROR_DIALOG_COLOR = "ffc2c2";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    private void setDialogBoxColor(String hexadecimalCode) {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        tmp.get(0).setStyle("-fx-background-color: #" + hexadecimalCode);
    }

    /**
     * Returns a DialogBox for the user with the given profile picture and message.
     *
     * @param text Text contained within the dialog box.
     * @param img Profile picture of the user.
     * @return DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a DialogBox for Athena with the given profile picture and message.
     *
     * @param text Text contained within the dialog box.
     * @param img Profile picture of Athena.
     * @return DialogBox for Athena.
     */
    public static DialogBox getAthenaDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setDialogBoxColor(ATHENA_DIALOG_COLOR);
        db.flip();
        return db;
    }

    /**
     * Returns an error DialogBox of Athena with the given profile picture and error message.
     *
     * @param errorMessage Error message for the error dialog.
     * @param img Profile picture of Athena.
     * @return Error DialogBox for Athena.
     */
    public static DialogBox getAthenaError(String errorMessage, Image img) {
        var db = new DialogBox(errorMessage, img);
        db.setDialogBoxColor(ATHENA_ERROR_DIALOG_COLOR);
        db.flip();
        return db;
    }
}
