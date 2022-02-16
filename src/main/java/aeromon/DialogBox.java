package aeromon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.Collections;

//@@author cashewnade-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html

/**
 * DialogBox class represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Text textBox;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs the DialogBox object.
     *
     * @param text the text in the DialogBox.
     * @param img  the image in the DialogBox.
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

        textBox.setText(text);
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
     * Gets the user dialog.
     *
     * @param text the text in the DialogBox.
     * @param img  the image in the DialogBox.
     * @return the new DialogBox object.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Gets the Aeromon dialog.
     *
     * @param text the text in the DialogBox.
     * @param img  the image in the DialogBox.
     * @return the new DialogBox object.
     */
    public static DialogBox getAeromonDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.textBox.setTextAlignment(TextAlignment.LEFT);
        return db;
    }
}