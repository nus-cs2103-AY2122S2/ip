package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/** Constructs DialogBox and controls its formatting in the Java GUI. */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs the DialogBox.
     * @param label Label to be stored in the DialogBox.
     * @param iv ImageView to be stored in the DialogBox.
     */
    public DialogBox(Label label, ImageView iv) {
        text = label;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /** Flips the dialog box such that the ImageView is on the left and text on the right. */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(Label label, ImageView iv) {
        return new DialogBox(label, iv);
    }

    public static DialogBox getDukeDialog(Label label, ImageView iv) {
        DialogBox dialogBox = new DialogBox(label, iv);
        dialogBox.flip();
        return dialogBox;
    }
}
