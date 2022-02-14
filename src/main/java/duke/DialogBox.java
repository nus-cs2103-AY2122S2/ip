package duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

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

    private DialogBox(String text, Image img, int type) {
        try {
            FXMLLoader fxmlLoader;
            if (type == 1) {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            } else {
                fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox2.fxml"));
            }
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        if (text.split("\\n").length > 1) {
            dialog.setTextAlignment(TextAlignment.JUSTIFY);
            System.out.println(text.split("\\n").length);
            dialog.setMinSize(200, Math.max((text.split("\\n").length) * 32.5, 100));
        } else {
            dialog.setMinSize(text.length() * 2, 10);
            dialog.setPrefWidth(text.length() * 20);
        }

        displayPicture.setImage(img);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, 1);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, 2);
        return db;
    }
}
