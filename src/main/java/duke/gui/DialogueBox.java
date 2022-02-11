package duke.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

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
        text = l;
        displayPicture = iv;
        displayPicture.setClip(new Circle(50, 50, 50));


        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        setMargin(text, new Insets(10, 10, 10, 10));
        setMargin(displayPicture, new Insets(10, 10, 10, 10));

        if (isUser) {
            setStyle("-fx-background-color: #1982FC; -fx-padding: 5 5 5 5; \n");
            l.setStyle("-fx-font-family: 'monospaced'; -fx-font-weight: bold; -fx-font-size: 14; " +
                    "-fx-text-fill: #FFFFFF");
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            setStyle("-fx-background-color: #EEEEEE; -fx-padding: 5 5 5 5; \n");
            l.setStyle("-fx-font-family: 'monospaced'; -fx-font-weight: bold; -fx-font-size: 14");
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }
    }
}
