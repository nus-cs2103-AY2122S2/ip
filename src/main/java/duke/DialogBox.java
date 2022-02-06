package duke;

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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label label;
    @FXML
    private ImageView imageView;

    private DialogBox(String text, Image image, Background background) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        label.setText(text);
        label.setBackground(background);
        imageView.setImage(image);
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

    public static DialogBox getUserDialog(String text, Image image) {
        Insets insets = new Insets(6);
        CornerRadii cornerRadii = new CornerRadii(10);
        DialogBox userDialog = new DialogBox(text, image,
                new Background(new BackgroundFill(Color.CORAL, cornerRadii, insets)));
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        return userDialog;
    }

    public static DialogBox getDukeDialog(String text, Image image) {
        Insets insets = new Insets(0);
        CornerRadii cornerRadii = new CornerRadii(10);
        DialogBox dukeDialog = new DialogBox(text, image,
                new Background(new BackgroundFill(Color.WHITE, cornerRadii, insets)));
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dukeDialog.flip();
        return dukeDialog;
    }

}