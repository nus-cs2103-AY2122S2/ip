package sana.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private static final BackgroundFill BACKGROUND_COLOR = new BackgroundFill(Paint.valueOf("aliceblue"),
            null, null);

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for the DialogBox object
     *
     * @param text  text in the DialogBox
     * @param img   image in the DialogBox
     */
    public DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);

        Circle imgCircle = new Circle(50, 50, 50);
        displayPicture.setImage(img);
        displayPicture.setClip(imgCircle);

        this.setBackground(new Background(BACKGROUND_COLOR));
        this.setPadding(new Insets(5, 5, 5, 5));
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
     * Returns a new DialogBox containing the user input
     *
     * @param l user input
     * @param iv    user image
     *
     * @return  DialogBox with user input
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        return db;
    }

    /**
     * Returns a new DialogBox containing sana;s response
     *
     * @param l     sana's response
     * @param iv    sana's image
     *
     * @return  DialogBox with sana's response
     */
    public static DialogBox getDukeDialog(String l, Image iv) {
        var db = new DialogBox(l, iv);
        db.setMinHeight(Region.USE_PREF_SIZE);
        db.flip();
        return db;
    }

}
