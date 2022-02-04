package duke.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogueBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a dialogue box.
     *
     * @param l  Text to be displayed.
     * @param iv Window to contain text.
     */
    public DialogueBox(Label l, ImageView iv, boolean isUser) {
        l.setStyle("-fx-font-family: 'monospaced'; -fx-font-weight: bold; -fx-font-size: 14");
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }
    }
}
