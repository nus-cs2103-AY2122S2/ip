package duke.ui.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * A HBox containing two elements: A text label
 * and an image avatar.
 *
 * Reused with modifications from JavaFX tutorial at
 * https://se-education.org/guides/tutorials/javaFx.html
 * by Jeffrey Lum.
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
        this.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
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
