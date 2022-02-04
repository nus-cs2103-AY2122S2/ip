package duke;

import java.io.IOException;

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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * An instance of DialogBox.
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
        dialog.setWrapText(true);
        dialog.setFont(new Font("Hasklig", 14));

        Rectangle clip = new Rectangle(displayPicture.getFitWidth(), displayPicture.getFitHeight());
        clip.setArcWidth(256);
        clip.setArcHeight(256);
        displayPicture.setImage(img);
        displayPicture.setClip(clip);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets user dialog.
     *
     * @param text string that the user had input
     * @param img  the image associated to the user
     * @return the user dialog
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var userDb = new DialogBox(text, img);
        userDb.setMinHeight(Region.USE_PREF_SIZE);
        return userDb;
    }

    /**
     * Gets duke dialog.
     *
     * @param text string that Duke has responded with
     * @param img  the image associated with Duke
     * @return the duke dialog
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var dukeDb = (text.isBlank() || text.isEmpty())
                ? new DialogBox("How can I help?", img)
                : new DialogBox(text, img);
        dukeDb.setMinHeight(Region.USE_PREF_SIZE);
        dukeDb.flip();
        return dukeDb;
    }
}
