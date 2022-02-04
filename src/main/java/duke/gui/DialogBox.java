package duke.gui;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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

    public static DialogBox getUserDialog(String l, Image iv) {
        Circle circle = new Circle(50, 50, 50);
        // iv.setClip(circle);
        var userDialogBox = new DialogBox(l, iv);
        userDialogBox.setSpacing(10);
        userDialogBox.setPadding(new Insets(15, 12, 15, 12));
        userDialogBox.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
        return userDialogBox;
    }

    public static DialogBox getDukeDialog(String l, Image iv) {
        Circle circle = new Circle(50, 50, 50);
        var dukeDialogBox = new DialogBox(l, iv);
        // iv.setClip(circle);
        dukeDialogBox.flip();
        dukeDialogBox.setSpacing(10);
        dukeDialogBox.setPadding(new Insets(15, 12, 15, 12));
        dukeDialogBox.setBackground(new Background(new BackgroundFill(Color.TOMATO, null, null)));
        return dukeDialogBox;
    }
}
