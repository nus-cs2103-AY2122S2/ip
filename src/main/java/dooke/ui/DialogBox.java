package dooke.ui;

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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Controller class representing a dialog box consisting of an ImageView to represent the
 * speaker's face and a label containing text from the speaker.
 * @author s7manth
 * @version 0.3
 */
public class DialogBox extends HBox {
    private static final Font CHAT_FONT = Font.loadFont(
            DialogBox.class.getResource("/fonts/JetBrainsMono-Regular.ttf").toExternalForm(), 12);

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructor for the DialogBox class.
     * @param text The text that would go into the dialog box.
     * @param image The accompanying image.
     */
    private DialogBox(String text, Image image) {
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
        dialog.setFont(CHAT_FONT);
        dialog.setBackground(new Background(new BackgroundFill(Color.rgb(44, 44, 44, 0.2),
                new CornerRadii(20), new Insets(-10, -10, -10, -10))));

        displayPicture.setImage(image);
        displayPicture.setFitWidth(40.0);
        displayPicture.setFitHeight(40.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        this.setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        setPadding(new Insets(10, 10, 10, 10));
    }

    /**
     * Obtains the user's dialog box.
     *
     * @param text The text for the dialog box.
     * @param image The accompanying image.
     * @return The resulting dialog box.
     */
    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox db = new DialogBox(text, image);
        return new DialogBox(text, image);
    }

    /**
     * Obtains the Dooke's dialog box.
     *
     * @param text The text for the dialog box.
     * @param image The accompanying image.
     * @return The resulting dialog box.
     */
    public static DialogBox getDookeDialog(String text, Image image) {
        DialogBox db = new DialogBox(text, image);
        db.flip();
        return db;
    }
}
