package duke.gui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A HBox containing two elements: A text label
 * and an image avatar.
 */
public class DialogBox extends HBox {

    /**
     * Returns a new DialogBox using the given label and
     * image as avatar.
     *
     * @param l Text label to display in box.
     * @param iv Image to display in box.
     */
    private DialogBox(Label l, ImageView iv) {
        l.setWrapText(true);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
        this.setSpacing(10);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a DialogBox for the user perspective, with text label
     * left of image.
     *
     * @param l Text label to display in box.
     * @param iv Image to display in box.
     * @return DialogBox object with given label and image as children.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns a DialogBox for the Duke's perspective, with text label
     * right of image.
     *
     * @param l Text label to display in box.
     * @param iv Image to display in box.
     * @return DialogBox object with given label and image as children.
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
