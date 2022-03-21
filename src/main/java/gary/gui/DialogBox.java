package gary.gui;

import gary.MainWindow;
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
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.Collections;

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

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // DialogBox background colour
        CornerRadii space = new CornerRadii(5);
        Insets offset = new Insets(5);
        Paint shade = Paint.valueOf("rgba(50, 110, 110, 0.3)");
        BackgroundFill fill = new BackgroundFill(shade, space, offset);
        this.setBackground(new Background(fill));

          dialog.setText(text);
          displayPicture.setImage(img);
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
     *
     * @param img
     * @return
     */
    public static DialogBox getWelcomeMessage(Image img) {
        var db = new DialogBox("  Gary here~", img);
        db.flip();
        return db;
    }

    /**
     * Creates DialogBox for User
     *
     * @param text user input
     * @param img user display picture
     * @return DialogBox for user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates DialogBox for Gary
     *
     * @param text gary's response
     * @param img Gary display picture
     * @return DialogBox for Gary
     */
    public static DialogBox getGaryDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}

