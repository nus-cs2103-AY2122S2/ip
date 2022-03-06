package duke.ui;

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
 * A custom control class using FXML.
 *
 * This control represents a dialog box that consists of an ImageView
 * to represent the speaker's face and a Label to contain the text
 * from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor to initialize an instance of DialogBox class with
     * text and img.
     *
     * @param text Text message
     * @param img Profile picture
     */
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
     * Flips the dialog box such that the ImageView is on the left
     * and Label is on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns the dialog box containing user message.
     *
     * @param text User text message
     * @param img User profile picture
     * @return The dialog box
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.getChildren().get(0).setStyle("-fx-background-color: #a9a9a9; -fx-background-radius: 10;");
        return dialogBox;
    }

    /**
     * Returns the dialog box containing bot response message.
     *
     * @param text Bot response text message
     * @param img Bot profile picture
     * @return The dialog box
     */
    public static DialogBox getDukeResponseDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.getChildren().get(0).setStyle("-fx-background-color: #499c54; -fx-background-radius: 10;");
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Returns the dialog box containing bot error message.
     *
     * @param text Bot error text message
     * @param img Bot profile picture
     * @return The dialog box
     */
    public static DialogBox getDukeErrorDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.getChildren().get(0).setStyle("-fx-background-color: #c75450; -fx-background-radius: 10;");
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Returns the dialog box containing bot reminder message.
     *
     * @param text Bot reminder text message
     * @param img Bot profile picture
     * @return The dialog box
     */
    public static DialogBox getDukeReminderDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.getChildren().get(0).setStyle("-fx-background-color: #3592C4; -fx-background-radius: 10;");
        dialogBox.flip();
        return dialogBox;
    }
}
