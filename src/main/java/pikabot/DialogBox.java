package pikabot;

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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
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

        Circle clip = new Circle(40, 40, 40);

        displayPicture.setClip(clip);

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
     * Gets dialog from user.
     *
     * @param text String text from user.
     * @param img Image consisting user's display picture.
     * @return Dialog from user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox userDialog = new DialogBox(text, img);
        userDialog.setStyle("-fx-background-color: #E0F0F3;" + "-fx-background-radius: 25;"
                + "-fx-padding: 5 0 10 0;"
                + "-fx-border-insets: 0 0 10 0;" + "-fx-background-insets: 0 0 10 0;");
        return userDialog;
    }

    /**
     * Gets dialog from the bot.
     *
     * @param text String text from bot.
     * @param img Image consisting bot's display picture.
     * @return Dialog from bot.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        var botDialog = new DialogBox(text, img);
        botDialog.setStyle("-fx-background-color: #FFF2CC;" + "-fx-background-radius: 25;"
                + "-fx-padding: 5 0 10 5;"
                + "-fx-border-insets: 0 0 10 0;" + "-fx-background-insets: 0 0 10 0;");
        botDialog.flip();
        return botDialog;
    }
}


