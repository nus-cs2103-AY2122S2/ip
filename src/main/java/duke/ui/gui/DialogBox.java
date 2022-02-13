package duke.ui.gui;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A custom control using FXML.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    private static final int CIRCLE_SIZE = 32;
    private static final String USER_DIALOG_COLOUR = "#ACB9C2";
    private static final String DUKE_DIALOG_COLOUR = "#8F9AA1";
    private static final String DIALOG_STYLE_PREFIX = "-fx-background-radius: 10; -fx-padding: 8; ";

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

        int circleRadius = CIRCLE_SIZE / 2;
        displayPicture.setClip(new Circle(circleRadius, circleRadius, circleRadius));

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Sets the dialog's background color.
     *
     * @param color Color in web format (eg. #FFFFFF).
     */
    public void setDialogBackgroundColour(String color) {
        dialog.setStyle(String.format("%s -fx-background-color: %s;",
                DIALOG_STYLE_PREFIX, color));
    }

    /**
     * Sets the dialog's background and text color to a predefined one for errors.
     */
    public void setErrorTextColor() {
        dialog.setStyle(String.format("%s -fx-background-color: %s;",
                DIALOG_STYLE_PREFIX, "ORANGE"));
        dialog.setTextFill(Color.WHITE);
    }

    /**
     * Creates a new User dialog.
     *
     * @param l Message to be shown in the dialog.
     * @param iv Profile image next to the dialog
     * @return Returns a new User dialog.
     */
    public static DialogBox getUserDialog(String l, Image iv) {
        DialogBox db = new DialogBox(l, iv);
        db.setDialogBackgroundColour(USER_DIALOG_COLOUR);
        return db;
    }

    /**
     * Creates a new Duke dialog.
     *
     * @param l Message to be shown in the dialog.
     * @param iv Profile image next to the dialog
     * @return Returns a new Duke dialog.
     */
    public static DialogBox getDukeDialog(String l, Image iv) {
        DialogBox db = new DialogBox(l, iv);
        db.flip();
        db.setDialogBackgroundColour(DUKE_DIALOG_COLOUR);
        return db;
    }

    /**
     * Creates a new Duke Error dialog.
     * Dialog is modified slightly with a different background and text color to highlight errors.
     *
     * @param l Message to be shown in the dialog.
     * @param iv Profile image next to the dialog
     * @return Returns a new Duke dialog.
     */
    public static DialogBox getDukeErrorDialog(String l, Image iv) {
        DialogBox db = getDukeDialog(l, iv);
        db.setErrorTextColor();
        return db;
    }
}
