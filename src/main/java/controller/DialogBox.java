package controller;
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
import javafx.scene.layout.Region;

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

    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setMinHeight(Region.USE_PREF_SIZE);
        dialog.setPrefWidth(270);
        if (isUser) {
            dialog.setStyle("-fx-background-color: #E6E9EF;");
        } else {
            dialog.setStyle("-fx-background-color: #6CC1E3;");
        }
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
     * Get user's input and translate it to a dialog box.
     *
     * @param l Message from the user
     * @param iv Image view to render the photos
     * @return DialogBox component
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l.getText(), iv.getImage(), true);
    }

    /**
     * Get user's input and translate it to a dialog box.
     *
     * @param text String format of the message from the user
     * @param img Image view to render the photos
     * @return DialogBox component
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Get bot's response and translate it to a dialog box.
     *
     * @param l Message to be displayed by the bot
     * @param iv Image view to render the photos
     * @return DialogBox component
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l.getText(), iv.getImage(), false);
        db.flip();
        return db;
    }

    /**
     * Get bot's response and translate it to a dialog box.
     *
     * @param text Message to be displayed by the bot
     * @param img Image view to render the photos
     * @return DialogBox component
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}
