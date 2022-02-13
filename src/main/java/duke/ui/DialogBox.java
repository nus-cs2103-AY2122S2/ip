package duke.ui;

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
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Represents a row in the chat window in the Ui.
 */
public class DialogBox extends HBox {
    @FXML
    private Label chat;
    @FXML
    private Circle displayPicture;
    @FXML
    private Label chatArrow;

    /**
     * Creates a new instance of <code>DialogBox</code>. Sole constructor for <code>DialogBox</code>.
     *
     * @param text The text to be inserted into the chat row.
     * @param image The profile picture of the user chatting.
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        chat.setText(text);
        ImagePattern pattern = new ImagePattern(image);
        displayPicture.setFill(pattern);
    }

    /**
     * Inverts the horizontal ordering of elements, from a label-image configuration to image-label.
     */
    private void changeLeftToRight() {
        ObservableList<Node> current = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(current);
        getChildren().setAll(current);
        setAlignment(Pos.TOP_LEFT);
        chatArrow.getStyleClass().add("inverted");
        HBox.setMargin(chatArrow, new Insets(10, 0, 0, 10));
    }

    /**
     * Creates a new instance of <code>DialogBox</code> to represent a row in the chat box for the user.
     * Performs an inversion of the horizontal ordering of the chat row to make it opposite of that of a
     * bot chat row.
     *
     * @param text The text to be inserted into the chat row.
     * @param image The profile picture of the user chatting.
     * @return The created instance of <code>DialogBox</code>
     */
    public static DialogBox getUserDialog(String text, Image image) {
        DialogBox row = new DialogBox(text, image);
        row.changeLeftToRight();
        return row;
    }

    /**
     * Creates a new instance of <code>DialogBox</code> to represent a row in the chat box for the user.
     * Performs an inversion of the horizontal ordering of the chat row to make it opposite of that of a
     * bot chat row.
     *
     * @param text The text to be inserted into the chat row.
     * @param image The profile picture of the bot.
     * @return The created instance of <code>DialogBox</code>
     */
    public static DialogBox getBotDialog(String text, Image image) {
        return new DialogBox(text, image);
    }
}
