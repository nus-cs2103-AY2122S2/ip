package duke.ui;

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

    private DialogBox(Label label, Image img, boolean isReturnError) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isReturnError) {
            dialog.setTextFill(Color.RED);
        }
        dialog.setText(label.getText());
        dialog.setGraphic(label.getGraphic());
        displayPicture.setImage(img);
        dialog.setMinHeight(USE_PREF_SIZE);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        tmp.get(0).setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 10px; -fx-padding: 10");
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets the user's dialog.
     *
     * @param label the label
     * @param img   the img
     * @return the user dialog
     */
    public static DialogBox getUserDialog(Label label, Image img) {
        return new DialogBox(label, img, false);
    }

    /**
     * Gets the Cortana's reply dialog. Response will be in red color if an error message is displayed.
     *
     * @param label         the label
     * @param img           the img
     * @param isReturnError whether Cortana replies an error message
     * @return the duke dialog
     */
    public static DialogBox getDukeDialog(Label label, Image img, boolean isReturnError) {
        var db = new DialogBox(label, img, isReturnError);
        db.flip();
        return db;
    }
}
