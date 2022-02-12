package spike.ui;

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
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
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

    /**
     * Returns the dialog box containing user message
     *
     * @param text user text message
     * @param img user profile picture
     * @return dialog box
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.getChildren().get(0).setStyle("-fx-background-color: #a9a9a9; -fx-background-radius: 10;");
        return dialogBox;
    }

    /**
     * Returns the dialog box containing bot message
     *
     * @param text bot text message
     * @param img bot profile picture
     * @return dialog box
     */
    public static DialogBox getSpikeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getChildren().get(0).setStyle("-fx-background-color: #3cb371; -fx-background-radius: 10;");
        db.flip();
        return db;
    }

    /**
     * Returns the dialog box containing bot error message
     *
     * @param text error text message
     * @param img bot profile picture
     * @return dialog box
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.getChildren().get(0).setStyle("-fx-background-color: #b22222; -fx-background-radius: 10;");
        db.flip();
        return db;
    }
}
