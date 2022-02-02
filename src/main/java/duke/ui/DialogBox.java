package duke.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(Label text, ImageView displayPicture) {
        this.text = text;
        this.displayPicture = displayPicture;

        this.text.setWrapText(true);
        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(this.text, this.displayPicture);
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

    public static DialogBox getUserDialog(Label text, ImageView displayPicture) {
        return new DialogBox(text, displayPicture);
    }

    public static DialogBox getDukeDialog(Label text, ImageView displayPicture) {
        DialogBox newDialogueBox = new DialogBox(text, displayPicture);
        newDialogueBox.flip();

        return newDialogueBox;
    }
}