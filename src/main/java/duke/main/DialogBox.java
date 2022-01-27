package duke.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


/**
 * DialogBox is the dialog box for the GUI when running Duke
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Creates a new DialogBox that has a Label (which is a text), and an Image to accompany the text
     *
     * @param l label for this particular DialogBox
     * @param iv an image for this DialogBox
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;


        l.setPadding(new Insets(10, 5, 0, 0));
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        Circle clip = new Circle(50, 40, 40);
        iv.setClip(clip);
        this.setAlignment(Pos.TOP_RIGHT);
        this.setPadding(new Insets(10, 2, 10, 2));
        this.getChildren().addAll(text, displayPicture);
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

    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}