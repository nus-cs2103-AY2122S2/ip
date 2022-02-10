package angela.gui;

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
 * A personalized dialog box for Angela bot
 */
public class DialogBox extends HBox {
    private static final String RESOURCE = "/view/DialogBox.fxml";
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;


    /**
     * Constructor for a dialog box
     *
     * @param text The text need to be display
     * @param img Profile image need to be display need to the text
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            loadFxml();
            setAttribute(text, img, isUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads FXML for the dialog box
     * @throws IOException If an I/O error occurs
     */
    private void loadFxml() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource(RESOURCE));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        fxmlLoader.load();
    }

    /**
     * Sets text and image attribute for the dialog box
     * @param text The text need to be displayed
     * @param img The image need to be displayed
     */
    private void setAttribute(String text, Image img, boolean isUser) {
        setTextLabel(text, isUser);
        setImage(img);
    }

    /**
     * Sets customize text dialog box for user and Angela
     *
     * @param text The displayed text
     * @param isUser If the dialog belong to user, return true, otherwise false
     */
    private void setTextLabel(String text, boolean isUser) {
        dialog.setText(text);
        CornerRadii cornerRadii = new CornerRadii(1.0);
        Insets insets = new Insets(35, 5, 10, 15);
        Color color;
        if (isUser) {
            color = Color.rgb(229, 246, 172, 0.96);
        } else {
            color = Color.rgb(172, 246, 224, 0.96);
        }
        Background background = new Background(new BackgroundFill(color, cornerRadii, insets));
        dialog.setBackground(background);
    }

    /**
     * Sets image picture for the dialog box
     *
     * @param img The displayed image
     */
    private void setImage(Image img) {
        setImageView(img);
        setSmoothImage(displayPicture);
    }

    /**
     * Sets image view from image
     *
     * @param img The displayed image
     */
    private void setImageView(Image img) {
        displayPicture.setImage(img);
    }

    /**
     * Enhance quality of the image picture
     *
     * @param imageView The image need to enhanced
     */
    private void setSmoothImage(ImageView imageView) {
        Circle clip = new Circle(40, 40, 55);
        imageView.setClip(clip);
    }

    /**
     * Flip the box from left-hand side to right-hand side
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Create dialog box object of user
     *
     * @param text Text need to be displayed on the box
     * @param img User profile image
     * @return New dialog box for user
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }

    /**
     * Create dialog box for Angela
     *
     * @param text Text need to be displayed on the box
     * @param img Angela profile image
     * @return New dialog box for Angela
     */
    public static DialogBox getAngelaDialog(String text, Image img) {
        var db = new DialogBox(text, img, false);
        db.flip();
        return db;
    }
}
