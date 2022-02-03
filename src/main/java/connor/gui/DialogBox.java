package connor.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Represents a box of dialog with text and an image.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView avatar;

    /**
     * Constructor for a DialogBox.
     *
     * @param text Text to be put in the dialog box.
     * @param img Image to be put in the dialog box.
     * @param isUser True if the dialog box is for the user, false otherwise.
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        avatar.setImage(img);
        avatar.setClip(getCircleClip());
        if (isUser) {
            dialog.setAlignment(Pos.CENTER_RIGHT);
        }
        this.setBackground(getColorBackground("#D3D3D3"));
    }

    /**
     * Flips the dialog box.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a {@code Background} with a single flat color given by colorString.
     *
     * @param colorString A String of the hex value of the color. E.g. "#FCC877".
     * @return A {@code Background} with a single flat color given by colorString.
     */
    private Background getColorBackground(String colorString) {
        Paint paint = Paint.valueOf(colorString);
        BackgroundFill bf = new BackgroundFill(paint, new CornerRadii(10, false), null);
        return new Background(bf);
    }

    /**
     * Creates a {@code Circle} with radius 25.
     *
     * @return A {@code Circle} with radius 25.
     */
    private Circle getCircleClip() {
        return new Circle(25, 25, 25);
    }

    /**
     * Returns a {@code DialogBox} for the user.
     *
     * @param s String of the text inputted by the user.
     * @param img Image of the user.
     * @return A {@code DialogBox} for the user.
     */
    public static DialogBox getUserDialog(String s, Image img) {
        return new DialogBox(s, img, true);
    }

    /**
     * Returns a {@code DialogBox} for Connor.
     *
     * @param s String of the text Connor responds with.
     * @param img Image of Connor.
     * @return A {@code DialogBox} for Connor.
     */
    public static DialogBox getConnorDialog(String s, Image img) {
        var db = new DialogBox(s, img, false);
        db.flip();
        return db;
    }

}
