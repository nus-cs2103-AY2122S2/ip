package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of a
 * Circle to represent the speaker's avatar and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setFill(new ImagePattern(img));
    }

    /**
     * Flips the dialog box such that the Circle is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Returns a DialogBox containing the user input and user avatar.
     *
     * @param text The user input.
     * @param img The user's avatar.
     * @return A DialogBox containing the specified text and avatar.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setBackground(new Background(new BackgroundFill(Paint.valueOf(String.valueOf(Color.LAVENDERBLUSH)),
                new CornerRadii(25), new Insets(5))));
        return db;
    }

    /**
     * Returns a DialogBox containing Duke's response and Duke's avatar.
     *
     * @param text Duke's response.
     * @param img Duke's avatar.
     * @return A DialogBox containing the specified text and avatar.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Paint.valueOf(String.valueOf(Color.MINTCREAM)),
                new CornerRadii(25), new Insets(5))));
        return db;
    }

    /**
     * Returns a DialogBox containing a reminder and Duke's avatar.
     *
     * @param text The reminder text.
     * @param img Duke's avatar.
     * @return A DialogBox containing the specified text and avatar.
     */
    public static DialogBox getReminderDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Paint.valueOf(String.valueOf(Color.LIGHTSKYBLUE)),
                new CornerRadii(25), new Insets(5))));
        return db;
    }
}
