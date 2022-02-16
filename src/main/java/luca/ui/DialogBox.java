package luca.ui;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import luca.Luca;

/**
 * Controller for DialogBox containing text and image.
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
        dialog.getStyleClass().add("dialog");
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
     * Outputs the Dialog Box with User image and input text.
     *
     * @param text user input text.
     * @param img user image.
     * @return User Dialog Box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var dialogBox = new DialogBox(text, img);
        dialogBox.dialog.setFont(Font.font("Comic Sans Ms", FontWeight.BOLD, 11));
        dialogBox.dialog.setTextFill(Color.WHITE);
        dialogBox.dialog.getStyleClass().add("user-dialog");
        return dialogBox;
    }

    /**
     * Outputs Dialog Box with Luca image and response text.
     *
     * @param text Response text.
     * @param img Luca image.
     * @return Luca Response Dialog Box.
     */
    public static DialogBox getLucaDialog(String text, Image img) {
        String[] messages = text.split(Luca.ERROR_PREFIX, 2);
        DialogBox dialogBox;
        if (messages.length == 2) { // Error message
            dialogBox = new DialogBox(messages[1], img);
            dialogBox.dialog.setTextFill(Color.ORANGE);
        } else {
            dialogBox = new DialogBox(text, img);
            dialogBox.dialog.setTextFill(Color.WHITE);
        }
        dialogBox.dialog.setFont(Font.font("Comic Sans Ms", 11));
        dialogBox.dialog.getStyleClass().add("luca-dialog");
        dialogBox.flip();
        return dialogBox;
    }

    /**
     * Outputs the welcome Dialog box.
     *
     * @param img image of Luca.
     * @return welcome Dialog Box.
     */
    public static DialogBox getLucaWelcome(Image img) {
        return getLucaDialog("Hi! I am Luca!\nHow may I help you?", img);
    }
}
