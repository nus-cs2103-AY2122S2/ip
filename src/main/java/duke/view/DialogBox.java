package duke.view;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final double USER_RED = 0.5216;
    private static final double USER_GREEN = 0.6667;
    private static final double USER_BLUE = 1.0;

    @FXML
    private Text dialog;
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
        dialog.setFont(new Font("Roboto", 12.5));
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
     * Generates a DialogBox to be displayed.
     * 
     * @param text text is the feedback to display to user.
     * @param image image is the user's image.
     * @return Returns a DialogBox with text and image embedded.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox userDialog = new DialogBox(text, image);

        userDialog.setPadding(new Insets(10, 0, 10, 10));
        userDialog.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.color(USER_RED, USER_GREEN, USER_BLUE),
                                new CornerRadii(0),
                                new Insets(0))));

        return userDialog;
    }

    /**
     * Generates a DialogBox to be displayed.
     * 
     * @param text text is the feedback to display to user.
     * @param image image is the user's image.
     * @return Returns a DialogBox with text and image embedded.
     */
    public static DialogBox getAbbyDialog(String text, Image image) {
        DialogBox abbyDialog = new DialogBox(text, image);

        abbyDialog.setPadding(new Insets(10, 0, 10, 10));
        abbyDialog.setBackground(
                new Background(
                        new BackgroundFill(
                                Color.GHOSTWHITE,
                                new CornerRadii(0),
                                new Insets(0))));
        abbyDialog.flip();

        return abbyDialog;
    }
}
