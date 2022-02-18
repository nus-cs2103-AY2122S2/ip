package mcbot.gui;

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
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

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
     * Constructor for dialog box whenever the user or chatbot response.
     * @param text is the text that is responded with.
     * @param img is the image to be displayed with the message.
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
        dialog.setFont(Font.font("Courier New", 12));
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Node lb = tmp.get(0);
        if (lb instanceof Label) {
            lb.setStyle("-fx-background-color:E6F2ED; -fx-background-radius: 30;"
                    + "-fx-label-padding:25;");
        }
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * Method to get the user dialog box.
     * @param text is the text given by the user.
     * @param img of the user.
     * @return DialogBox of the text with image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox dialogBox = new DialogBox(text, img);
        dialogBox.setMinHeight(Region.USE_PREF_SIZE);
        dialogBox.getChildren().get(0).setStyle("-fx-background-color:E6F2ED; -fx-background-radius:30;"
                + "-fx-label-padding:25;");
        dialogBox.setAlignment(Pos.CENTER_RIGHT);
        return dialogBox;
    }

    /**
     * Method to get the McBot dialog box.
     * @param text is the text responded by the bot.
     * @param img of the bot.
     * @return DialogBox of the text with image.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setMinHeight(Region.USE_PREF_SIZE);
        return db;
    }
}