package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * <h1>DialogBox</h1>
 * <p>
 * Creates dialog boxes for GUI.
 * </p>
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * creates a dialog box.
     *
     * @param l the label of the dialog box.
     * @param iv the image to use.
     */
    public DialogBox(Label l, ImageView iv) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
        this.setSpacing(10);
        this.setBackground(new Background(new BackgroundFill(Color.ORANGE, null, null)));

    }

    /**
     * flips the order of the image and label.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * returns an instance of user dialog box.
     *
     * @param l the label of the dialog box.
     * @param iv the image of the user.
     * @return a dialog box representing user input.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * returns an instance of conan dialog box.
     *
     * @param l the label of the dialog box.
     * @param iv the image of the user.
     * @return a dialog box representing conan's response.
     */
    public static DialogBox getConanDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
        return db;
    }
}
