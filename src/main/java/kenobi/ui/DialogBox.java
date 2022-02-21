package kenobi.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.Collections;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    enum Alignment {
        RIGHT, LEFT
    }

    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, Alignment alignment) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);

        if (alignment.equals(Alignment.RIGHT)) {
            dialog.setTextAlignment(TextAlignment.RIGHT);
            setAlignment(Pos.CENTER_RIGHT);
        } else if (alignment.equals(Alignment.LEFT)) {
            ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
            Collections.reverse(tmp);
            getChildren().setAll(tmp);
            setAlignment(Pos.CENTER_LEFT);
        }
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, Alignment.RIGHT);
    }

    public static DialogBox getKenobiDialog(String text, Image img) {
        var db = new DialogBox(text, img, Alignment.LEFT);
        return db;
    }
}
