package dukelauncher;

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
import javafx.scene.paint.Paint;

/**
 * Custom control DialogBox class for Duke
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

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAsDukeBox();
        return db;
    }

    /**
     * Sets this Dialog box to be in the style of a response box
     * 1. Flips the dialog box such that the ImageView is on the left and text on the right.
     * 2. Flips the label position to accommodate the dialog box flip
     */
    private void setAsDukeBox() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);

        for (Node n : getChildren()) {
            if (n instanceof Label) { //locate the label of the Dialog Box
                Label l = (Label) n;
                l.setTranslateX(5);
                l.setTextFill(Paint.valueOf("#881111"));
            }
        }
        setAlignment(Pos.TOP_LEFT);
    }
}
