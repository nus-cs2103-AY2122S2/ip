package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for DialogBox.
     * @param label the Label containing the text to be displayed
     * @param imageView the ImageView containing the image to be displayed
     */
    public DialogBox(Label label, ImageView imageView) {
        text = label;
        displayPicture = imageView;

        text.setWrapText(true);
        text.setPadding(new Insets(0, 5, 0, 5));
        text.setBorder(new Border(new BorderStroke(
                Color.BLACK,
                new BorderStrokeStyle(null, null, null, 10, 0, null),
                new CornerRadii(10),
                new BorderWidths(1.0))));
        text.setBackground(new Background(new BackgroundFill(Color.AQUA, new CornerRadii(10), null)));

        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        Circle clip = new Circle(50, 50, 50);
        displayPicture.setClip(clip);

        this.setPadding(new Insets(5, 1, 5, 1));

        this.setAlignment(Pos.TOP_RIGHT);
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
