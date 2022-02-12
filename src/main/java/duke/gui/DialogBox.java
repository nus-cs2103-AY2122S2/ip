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
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;

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

    /**
     * Constructor for the {@code DialogBox} object.
     */
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
        label.setTextFill(Color.BLACK);
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

    /**
     * Returns the dialog box for the user side.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        Insets insets = new Insets(6);
        CornerRadii cornerRadii = new CornerRadii(10);
        DialogBox userDialog = new DialogBox(text, image,
                new Background(new BackgroundFill(Color.CORAL, cornerRadii, insets)));
        userDialog.setMinHeight(Region.USE_PREF_SIZE);
        return userDialog;
    }

    /**
     * Returns the dialog box for the Duke side.
     */
    public static DialogBox getDukeDialog(String text, Image image) {
        Insets insets = new Insets(6);
        CornerRadii cornerRadii = new CornerRadii(10);
        DialogBox dukeDialog = new DialogBox(text, image,
                new Background(new BackgroundFill(Color.WHITE, cornerRadii, insets)));
        dukeDialog.setMinHeight(Region.USE_PREF_SIZE);
        dukeDialog.flip();
        return dukeDialog;
    }

}