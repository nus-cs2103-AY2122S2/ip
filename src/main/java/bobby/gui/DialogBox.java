package bobby.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox
     *
     * @param text The text message to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     * @param isUser Determines if image is to be on the left or right.
     * @param isErrorBox Determines if the font is to be bold or not.
     */
    private DialogBox(String text, Image img, boolean isUser, boolean isErrorBox) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setTextAlignment(isUser ? TextAlignment.RIGHT : TextAlignment.LEFT);
        if (isErrorBox) {
            dialog.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        }
        displayPicture.setImage(img);
        displayPicture.setClip(new Circle(50, 50, 50));
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

    /**
     * Creates a dialog box for the user.
     *
     * @param text The text message to be displayed in the dialog box.
     * @param img The user image.
     * @return The user dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, true, false);
        db.setStyle(db.getStyle() + "-fx-background-color: #B0E0E6");
        return db;
    }

    /**
     * Creates a dialog box for Bobby.
     *
     * @param text The text message to be displayed in the dialog box.
     * @param img The Bobby image.
     * @return The Bobby dialog box.
     */
    public static DialogBox getBobbyDialog(String text, Image img) {
        var db = new DialogBox(text, img, false, false);
        db.setStyle(db.getStyle() + "-fx-background-color: #66CDAA");
        db.flip();
        return db;
    }

    /**
     * Creates a dialog box for the Error.
     *
     * @param text The text message to be displayed in the dialog box.
     * @param img The Bobby image.
     * @return The Error dialog box.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img, false, true);
        db.setStyle(db.getStyle() + "-fx-background-color: #FF6347");
        db.flip();
        return db;
    }
}
