package li.zhongfu.cs2103.chatbot.ui.gui;

import java.io.IOException;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MessageBubble extends AnchorPane {
    @FXML
    private VBox vBox;
    @FXML
    private Text mainText;
    @FXML
    private Text metaText;

    private MessageBubble() {
        FXMLLoader fxmlLoader = new FXMLLoader(MessageBubble.class.getResource("/view/MessageBubble.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void bindWidthToProperty(ObservableValue<? extends Number> property) {
        mainText.wrappingWidthProperty().bind(property);
    }


    private void setBubbleColor(Color color) {
        setStyle(String.format("bg-color: %s;", colorToHexString(color)));
    }

    private String colorToHexString(Color color) {
        int r = (int) Math.round(color.getRed() * 255.0);
        int g = (int) Math.round(color.getGreen() * 255.0);
        int b = (int) Math.round(color.getBlue() * 255.0);
        return String.format("#%02x%02x%02x", r, g, b);
    }

    /**
     * Creates a new message bubble.
     *
     * @param text the text for the main message
     * @param metadata the text for the metadata
     * @param bubbleColor the color of the bubble
     * @return a new MessageBubble instance
     */
    public static MessageBubble create(String text, String metadata, Color bubbleColor) {
        MessageBubble mb = new MessageBubble();
        mb.setBubbleColor(bubbleColor);
        mb.mainText.setText(text);
        mb.metaText.setText(metadata);
        return mb;
    }
}
