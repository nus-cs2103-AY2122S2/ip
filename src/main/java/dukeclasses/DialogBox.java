package dukeclasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a box used in the printing of conversation in the GUI.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     *
     * @param text Text to be displayed in the box
     * @param displayPicture Picture to be displayed on the right hand side of the box.
     */
    public DialogBox(Label text, ImageView displayPicture) {
        this.text = text;
        this.displayPicture = displayPicture;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Reverses order for the dialogBox
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> temp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(temp);
        this.getChildren().setAll(temp);
    }

    /**
     * Creates dialogBox for user
     */
    public static DialogBox getUserDialog(Label label, ImageView imageToView) {
        return new DialogBox(label, imageToView);
    }

    /**
     * Creates dialogBox for Duke
     */
    public static DialogBox getDukeDialog(Label label, ImageView imageToView) {
        DialogBox dialogBox = new DialogBox(label, imageToView);
        dialogBox.flip();
        return dialogBox;
    }

}