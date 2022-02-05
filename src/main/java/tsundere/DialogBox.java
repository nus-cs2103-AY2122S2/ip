package tsundere;

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
import javafx.scene.layout.HBox;

/**
 * Controller for the box that occurs during dialog
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a new DialogBox object.
     *
     * @param text string that will appear as the dialog.
     * @param img image for the dialog.
     */
    public DialogBox(String text, Image img) {
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

    /**
     * Returns a new dialogBox for user.
     *
     * @param text string that will appear as the dialog.
     * @param img image for the dialog.
     * @return DialogBox object for user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a new dialogBox for AI.
     *
     * @param text string that will appear as the dialog.
     * @param img image for the dialog.
     * @param isChain is an continuation from the previous dialog as there is not enough space for one DialogBox.
     * @return a new dialogBox for AI.
     */
    public static DialogBox getTsundereDialog(String text, Image img, Boolean isChain) {
        var db = new DialogBox(text, img);
        if (isChain) {
            db.setPadding(new Insets(0, 5, 15, 5));
            db.displayPicture.setVisible(false);
        }
        db.flip();
        return db;
    }


}
