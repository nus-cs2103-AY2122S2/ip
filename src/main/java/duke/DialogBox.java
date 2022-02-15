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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import javax.swing.*;

/**
 * An example of a custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {

    enum Position {
        Robot, User
    }

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img, Position position) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SetTextWithColour(text, img);

        //@@author tiewweijian-reused
        //Reused from https://github.com/jonfoocy/ip/blob/master/src/main/java/DialogBox.java with modifications
        if (position.equals(Position.User)) {
            dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(15.0),
                    Insets.EMPTY)));
            dialog.setPadding(new Insets(8));
        } else if (position.equals(Position.Robot)) {
            dialog.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(15.0),
                    Insets.EMPTY)));
            dialog.setPadding(new Insets(8));
        }
        //@author jonfoocy

    }

    private void SetTextWithColour(String text, Image img) {
        dialog.setText(text);
        displayPicture.setImage(img);

        if (text.contains("$HIGH")) {
            dialog.setTextFill(Color.RED);
        } else if (text.contains("$MEDIUM")) {
            dialog.setTextFill(Color.YELLOW);
        } else if (text.contains("$LOW")) {
            dialog.setTextFill(Color.GREEN);
        }
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
        return new DialogBox(text, img, Position.User);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img, Position.Robot);
        db.flip();
        return db;
    }
}
