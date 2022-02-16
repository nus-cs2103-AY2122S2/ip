package duke.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    private static final String commonDialogStyles = "-fx-font-family: \"Courier New\"; "
            + "-fx-font-size: 12; " + "-fx-background-radius: 7; " + "-fx-padding: 7;";
    private static final String dukeDialogBackgroundStyles = "-fx-background-color: #d6e7d6;";
    private static final String userDialogBackgroundStyles = "-fx-background-color: #ead8eb;";


    @FXML
    private Text dialog;
    @FXML
    private ImageView displayPicture;
    @FXML
    private StackPane chatBubble;

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
        displayPicture.setClip(createClippingCircle(img));
    }

    private Circle createClippingCircle(Image img) {
        double centerX = displayPicture.getX() + displayPicture.getFitWidth() / 2;
        double centerY = displayPicture.getY() + displayPicture.getFitHeight() / 2;
        double radius = displayPicture.getFitWidth() / 2;
        return new Circle(centerX, centerY, radius);
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

    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);
        userDialog.chatBubble.setStyle(commonDialogStyles + userDialogBackgroundStyles);
        userDialog.dialog.setTextAlignment(TextAlignment.RIGHT);
        return userDialog;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        DialogBox dukeDialog = new DialogBox(text, img);
        dukeDialog.flip();
        dukeDialog.chatBubble.setStyle(commonDialogStyles + dukeDialogBackgroundStyles);
        return dukeDialog;
    }
}
