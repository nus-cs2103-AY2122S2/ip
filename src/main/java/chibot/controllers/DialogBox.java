package chibot.controllers;

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

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for the class.
     *
     * @param text The text to be placed into the DialogBox.
     * @param img The image to be set in the DialogBox
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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox for the user.
     *
     * @param text Text typed by user.
     * @param img Image for representing the user.
     * @return A new DialogBox.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox for Chi.
     *
     * @param text Text response of Chi.
     * @param img Image for representing Chi.
     * @return A new DialogBox.
     */
    public static DialogBox getChiDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("chiMsg");
        db.flip();
        return db;
    }

    /**
     * Creates an Error DialogBox for Chi.
     *
     * @param text Text error response of Chi.
     * @param img Image for representing Chi.
     * @return A new Error DialogBox.
     */
    public static DialogBox getChiErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getStyleClass().add("chiMsgError");
        db.flip();
        return db;
    }
}
