package funbox;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class  DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle imageCircle;

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
        imageCircle.setStroke(Color.WHITESMOKE);
        imageCircle.setFill(new ImagePattern(img));
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Displays the user dialog.
     *
     * @param text Gets the user text.
     * @param img Gets the user image.
     * @return Returns a dialog box to be displayed.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setPadding(new Insets(10, 10, 10, 10));
        return db;
    }

    /**
     * Displays the bot dialog.
     *
     * @param text Gets the bot's text.
     * @param img Gets the bot's image.
     * @return Returns a dialog box to be displayed.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.dialog.setPadding(new Insets(10, 10, 10, 10));
        db.flip();
        return db;
    }
}