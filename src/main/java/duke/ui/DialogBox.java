package duke.ui;

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
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox object to store user command and Duke reply.
     * @param text User command or Duke response.
     * @param img User image or Duke image
     */
    public DialogBox(String text, Image img, Color color, Boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        dialog.setFont(Font.font("Verdana", 15));
        dialog.setMinSize(0, 30);
        dialog.setPadding(new Insets(10, 15, 10, 15));
        dialog.setBackground(new Background(new BackgroundFill(
                color,
                new CornerRadii(20),
                null
        )));
        Circle clip = new Circle(35, 35, 35);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
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

    public static DialogBox getUserDialog(String text, Image img) {
        Color color = new Color(0.2, 0.8, 0.8, .95);
        DialogBox db = new DialogBox(text, img, color, true);
        db.setSpacing(12);
        db.setPadding(new Insets(10, 10, 15, 20));

        return db;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        Color color = new Color(0.8, 0.8, 0.8, .95);
        DialogBox db = new DialogBox(text, img, color, false);
        db.setSpacing(12);
        db.setPadding(new Insets(10, 10, 10, 10));
        db.flip();

        return db;
    }
}
