package pikabot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox.
     *
     * @param l Label text.
     * @param iv ImageView consisting display picture.
     */
    public DialogBox(Label l, ImageView iv) {
        this.text = l;
        this.displayPicture = iv;

        this.text.setWrapText(true);
        this.displayPicture.setFitWidth(100.0);
        this.displayPicture.setFitHeight(100.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }
    /**
     *  Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets dialog from user.
     *
     * @param l Label text from user.
     * @param iv ImageView consisting user's display picture.
     * @return Dialog from user.
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Gets dialog from the bot.
     *
     * @param l Label text from the bot.
     * @param iv ImageView consisting bot's display picture.
     * @return Dialog from bot.
     */
    public static DialogBox getBotDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}

