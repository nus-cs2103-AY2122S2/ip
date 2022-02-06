package spark.ui;

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

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class UserDialogBox extends DialogBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    UserDialogBox(String text, Image img) {
        assert(text != null);
        assert(img != null);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/UserDialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    public static UserDialogBox getDialog(String text, Image img) {
        UserDialogBox dialogBox = new UserDialogBox(text, img);
        dialogBox.dialog.setBackground(DialogBox.getNormalChatboxColor());

        return dialogBox;
    }
}
