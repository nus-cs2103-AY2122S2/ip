package baron.ui;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private Circle displayPicture;

    /**
     * Constructs a {@code DialogBox} node that acts as dialog box in a chat.
     *
     * @param text text to display in the dialog box.
     * @param img image to display as the avatar alongside the dialog box.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/views/DialogBox.fxml"));
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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Constructs a blue {@code DialogBox} for the user, which is on the right-hand side.
     *
     * @param text text to display in the dialog box.
     * @param img image to display as the avatar alongside the dialog box.
     * @return the constructed {@code DialogBox}.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Constructs a white {@code DialogBox} for the user, which is on the left-hand side.
     *
     * @param text text to display in the dialog box.
     * @param img image to display as the avatar alongside the dialog box.
     * @return the constructed {@code DialogBox}.
     */
    public static DialogBox getBaronDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        db.dialog.setStyle("-fx-border-radius: 10 10 10 10; -fx-background-radius: 10 10 10 10;"
                + "-fx-background-color: #FFFFFF;");
        return db;
    }
}
