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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
     * Initialize a DialogBox with a text label and a corresponding image.
     * @param text Text label.
     * @param img Image corresponding to the user type.
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

        dialog.setText(text);
        displayPicture.setImage(img);
        Circle clip = new Circle(40, 40, 40);
        displayPicture.setClip(clip);
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
     * Method that alters the design of the dialog box depending on the type of user.
     * @param isUser Boolean to indicate if the person is a user or Duke.
     */
    private void setDesign(boolean isUser) {
        String backgroundColor = isUser ? "#e75480" : "#f0efeb";
        String textColor = isUser ? "#FFF" : "#000";
        Color textColorFill = Color.web(textColor);
        Background dukeBackground = new Background(new BackgroundFill(
                Color.web(backgroundColor), new CornerRadii(20), new Insets(5, 3, 3, 2)));
        this.dialog.setBackground(dukeBackground);
        this.dialog.setTextFill(textColorFill);
    }

    /**
     * Method that returns the dialog box of the user.
     * On the JavaFX application, it is the user on the right of the screen.
     * @param text Text label.
     * @param img User image.
     * @return User dialog box.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox box = new DialogBox(text, img);
        box.setDesign(true);
        return box;
    }


    /**
     * Method that returns the dialog box of Duke.
     * On the JavaFX application, it is the user on the left of the screen.
     * @param text Text label.
     * @param img Duke's image.
     * @return Duke's dialog box.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.setDesign(false);
        return db;
    }
}
