package angela.gui;

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
import javafx.scene.shape.Circle;

/**
 * A personalized dialog box for Angela bot
 */
public class DialogBox extends HBox {
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
        setSmoothImage(displayPicture);
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
        // Background color for user dialog
        //l.setBackground(new Background(new BackgroundFill(Color.rgb(229, 246, 172, 0.96),
        //        new CornerRadii(1.0), new Insets(35, 5, 10, 15))));
        return new DialogBox(text, img);
    }

    /**
     * Create dialog box for Angela
     *
     * @param text Text need to be displayed on the box
     * @param img Angela profile image
     * @return New dialog box for Angela
     */
    public static DialogBox getAngelaDialog(String text, Image img) {
        // Background color for Angela dialog
        //l.setBackground(new Background(new BackgroundFill(Color.rgb(172, 246, 224, 0.96),
        //        new CornerRadii(1.0), new Insets(35, 5, 10, 15))));
        var db = new DialogBox(text, img);
        db.flip();
        return db;
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
}
