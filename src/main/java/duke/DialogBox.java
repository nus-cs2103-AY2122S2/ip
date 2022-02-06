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

        dialog.setWrapText(true);
        displayPicture.setFitWidth(120.0);
        displayPicture.setFitHeight(120.0);

        Circle circle = new Circle(50);
        circle.setStroke(Color.WHITE);
        circle.setStrokeWidth(10);
        circle.setCenterX(displayPicture.getFitWidth() / 2);
        circle.setCenterY(displayPicture.getFitHeight() / 2);
        displayPicture.setClip(circle);
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
     * Change the Background color of the DialogBox.
     *
     * @param color Color of the Background.
     */
    private void changeBackGround(Color color) {
        BackgroundFill backgroundFill = new BackgroundFill(color,
                CornerRadii.EMPTY, Insets.EMPTY);

        Background background = new Background(backgroundFill);

        this.setBackground(background);
    }

    /**
     * Method to call to produce a DialogBox for User.
     *
     * @param text Text associated with User DialogBox.
     * @param img Image of User DialogBox.
     * @return DialogBox for User.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.changeBackGround(Color.LIGHTSKYBLUE);
        return db;
    }

    /**
     * Method to call to produce a DialogBox from Duke.
     *
     * @param text Text associated with Duke DialogBox.
     * @param img Image of Duke DialogBox.
     * @return DialogBox for Duke.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeBackGround(Color.LIGHTGREY);
        return db;
    }
}
