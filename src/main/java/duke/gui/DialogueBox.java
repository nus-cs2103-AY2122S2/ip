package duke.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * Represents a dialogue box in Duke GUI.
 */
public class DialogueBox extends HBox {
    private Label text;
    private ImageView displayPicture;
    private static final int IMAGE_SIZE = 80;

    /**
     * Constructor for a dialogue box.
     *
     * @param label Text to be displayed.
     * @param image Window to contain text.
     */
    public DialogueBox(Label label, ImageView image, boolean isUser) {
        text = label;
        displayPicture = image;

        int halfImageSize = IMAGE_SIZE / 2;
        displayPicture.setClip(new Circle(halfImageSize, halfImageSize, halfImageSize));

        text.setWrapText(true);
        displayPicture.setFitWidth(IMAGE_SIZE);
        displayPicture.setFitHeight(IMAGE_SIZE);
        setMargin(text, new Insets(10, 10, 10, 10));
        setMargin(displayPicture, new Insets(10, 10, 10, 10));

        if (isUser) {
            setStyle("-fx-background-color: #1982FC; -fx-padding: 5 5 5 5; \n");
            text.setStyle("-fx-font-family: 'monospaced'; -fx-font-weight: bold; -fx-font-size: "
                    + "12; -fx-text-fill: #FFFFFF");
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            setStyle("-fx-background-color: #EEEEEE; -fx-padding: 5 5 5 5; \n");
            text.setStyle("-fx-font-family: 'monospaced'; -fx-font-weight: bold; -fx-font-size: "
                    + "12");
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }
    }
}
