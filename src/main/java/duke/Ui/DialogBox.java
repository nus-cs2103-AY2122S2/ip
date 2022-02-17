package duke.ui;

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
import javafx.scene.layout.HBox;

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

    private DialogBox(String text, Image img, FXMLLoader fxmlLoader) {
        try {
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public static DialogBox getDialog(String text, Image img, DialogBoxStyle dialogBoxStyle) {
        FXMLLoader fxmlLoader;

        switch (dialogBoxStyle) {
        case UserError:
            fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogErrorBox.fxml"));
            break;
        case BotNormal:
            fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/BotDialogBox.fxml"));
            break;
        case BotError:
            fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/BotDialogErrorBox.fxml"));
            break;
        default:
            fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
        }

        var db = new DialogBox(text, img, fxmlLoader);
        db.flip();
        return db;
    }
}

