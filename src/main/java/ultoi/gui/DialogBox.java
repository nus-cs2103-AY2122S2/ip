package ultoi.gui;

import java.io.IOException;

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
import javafx.scene.layout.Region;

/**
 * Controls the dialog box in the application.
 *
 * @author snoidetx
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

        setMinHeight(Region.USE_PREF_SIZE);
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    // Flips the dialog box such that the ImageView is on the left and text on the right.
    // Changes the text box colour.
    private void altSide() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        tmp.get(0).setStyle("-fx-background-color: #323141; -fx-background-radius: 6px");
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    // Shows a text box that contains an error message.
    private void altError() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        tmp.get(0).setStyle("-fx-background-color: #323141; -fx-background-radius: 6px; -fx-text-fill: #ffff00");
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Converts user input into a dialog box.
     *
     * @param text User input.
     * @param image User profile picture.
     * @return User dialog box.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Converts Ultoi response into a dialog box.
     *
     * @param text Ultoi response.
     * @param image Ultoi profile picture.
     * @return Ultoi dialog box.
     */
    public static DialogBox getUltoiDialog(String text, Image image) {
        var db = new DialogBox(text, image);
        db.altSide();
        return db;
    }

    /**
     * Converts an error message into a dialog box.
     *
     * @param text Error message.
     * @param image Ultoi profile picture.
     * @return Ultoi error dialog box.
     */
    public static DialogBox getUltoiErrorDialog(String text, Image image) {
        var db = new DialogBox(text, image);
        db.altError();
        return db;
    }
}
