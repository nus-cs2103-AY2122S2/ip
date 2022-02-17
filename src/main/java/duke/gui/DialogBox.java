package duke.gui;

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

    /**
     * Creates a default dialog box
     *
     * @param text text to be displayed in the dialog box
     * @param img user image
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.getStyleClass().add("normal-msg");
        dialog.setText(text);
        displayPicture.setImage(img);
        double imgCenter = displayPicture.getFitHeight() / 2;
        displayPicture.setClip(new Circle(imgCenter, imgCenter, imgCenter));
    }

    /**
     * Creates a dialog box with the specified font type
     *
     * @param text      text to be displayed in the dialog box
     * @param img       image of user
     * @param fontType  custom font type
     */
    private DialogBox(String text, Image img, String fontType) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fontType.equalsIgnoreCase("error")) {
            dialog.getStyleClass().add("error-msg");
        }
        dialog.setText(text);
        displayPicture.setImage(img);
        double imgCenter = displayPicture.getFitHeight() / 2;
        displayPicture.setClip(new Circle(imgCenter, imgCenter, imgCenter));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        dialog.setAlignment(Pos.BASELINE_LEFT);
        setAlignment(Pos.TOP_LEFT);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    public static DialogBox getDukeDialogCustom(String text, Image img, String fontType) {
        var db = new DialogBox(text, img, fontType);
        db.flip();
        return db;
    }
}
