package duke;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

// Solution below adopted from https://se-education.org/guides/tutorials/javaFx.html

/**
 * Represents the dialog box as part of the GUI.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs an instance of the DialogBox class.
     *
     * @param l the Label representing the text to be displayed.
     * @param iv the ImageView representing the image to be displayed.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box to the opposite side of the window.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a dialog box for the user.
     *
     * @param l the Label representing the text to be displayed.
     * @param iv the ImageView representing the image to be displayed.
     * @return the DialogBox containing the label and image.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Creates a dialog box for the system.
     *
     * @param l the Label representing the text to be displayed.
     * @param iv the ImageView representing the image to be displayed.
     * @return the DialogBox containing the label and image.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
