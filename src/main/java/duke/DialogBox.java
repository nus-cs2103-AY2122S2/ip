package duke;

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
        // @@yunbinmo punnyhuimin-reused
        // Reused from https://github.com/yunbinmo/ip/blob/master/src/main/java/spike/ui/DialogBox.java
        // Sets the color of the dialog box based on who is talking. Minor modifications made to the color to
        // match Ducky.
        userDialog.getChildren().get(0).setStyle("-fx-background-color: #3d341a; -fx-background-radius: 10;");
        // @@yubinmo
        return userDialog;
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        // @@yunbinmo punnyhuimin-reused
        // Reused from https://github.com/yunbinmo/ip/blob/master/src/main/java/spike/ui/DialogBox.java
        // Sets the color of the dialog box based on who is talking. Minor modifications made to the color to
        // match Ducky.
        db.getChildren().get(0).setStyle("-fx-background-color: #bd8b04; -fx-background-radius: 10;");
        // @@yunbinmo
        db.flip();
        return db;
    }

    public static DialogBox getWarningDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        // @@yunbinmo punnyhuimin-reused
        // Reused from https://github.com/yunbinmo/ip/blob/master/src/main/java/spike/ui/DialogBox.java
        // Sets the color of the dialog box based on who is talking. Minor modifications made to the color to
        // match Ducky.
        db.getChildren().get(0).setStyle("-fx-background-color: #f56c42; -fx-background-radius: 10;");
        // @@yunbinmo
        db.flip();
        return db;
    }
}