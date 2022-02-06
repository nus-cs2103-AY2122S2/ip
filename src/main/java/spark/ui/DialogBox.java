package spark.ui;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * Represents a DialogBox that appears as a single chat-bubble
 * sent by either the user or Spark on the GUI
 */
public abstract class DialogBox extends HBox {
    protected static final Color NORMAL_CHATBOX_COLOR = Color.web("#FFFFFF");
    protected static final Color SUCCESS_CHATBOX_COLOR = Color.web("#BEE5B0");
    protected static final Color WARNING_CHATBOX_COLOR = Color.web("#F8F1AE");
    protected static final Color ERROR_CHATBOX_COLOR = Color.web("#FFB2AE");

    protected static Background getNormalChatboxColor() {
        return new Background(
                new BackgroundFill(
                        DialogBox.NORMAL_CHATBOX_COLOR,
                        new CornerRadii(20),
                        new Insets(0)));
    }

    protected static Background getSuccessChatboxColor() {
        return new Background(
                new BackgroundFill(
                        DialogBox.SUCCESS_CHATBOX_COLOR,
                        new CornerRadii(20),
                        new Insets(0)));
    }

    protected static Background getWarningChatboxColor() {
        return new Background(
                new BackgroundFill(DialogBox.WARNING_CHATBOX_COLOR,
                        new CornerRadii(20),
                        new Insets(0)));
    }

    protected static Background getErrorChatboxColor() {
        return new Background(
                new BackgroundFill(DialogBox.ERROR_CHATBOX_COLOR,
                        new CornerRadii(20),
                        new Insets(0)));
    }
}
