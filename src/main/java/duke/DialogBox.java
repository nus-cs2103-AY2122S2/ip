package duke;

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
 * This is a class that handles with the logic of the Dialog Box
 * of the Duke and user, providing response to user inputs
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-2-3
 */

public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates the UI of the DialogBox for both Duke and User generally
     * @param text placeholder for the User input/response from Duke
     * @param img placeholder for the image of the Duke/User
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/duke/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        dialog.setWrapText(true);
        displayPicture.setImage(img);
    }

    /** Filps the orientation of dialog box of Duke and User */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /** Obtains DialogBox for User
     *  @param text placeholder for the User input/response from Duke
     *  @param img placeholder for the image of the Duke/User
     **/
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_RIGHT);
        return new DialogBox(text, img);
    }

    /** Obtains DialogBox for Duke
     *  @param text placeholder for the User input/response from Duke
     *  @param img placeholder for the image of the Duke/User
     **/
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setAlignment(Pos.CENTER_LEFT);
        db.flip();
        return db;
    }
}