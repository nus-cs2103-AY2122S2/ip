package li.zhongfu.cs2103.chatbot.ui.gui;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Creates a new JavaFX message bubble.
 */
public class MessageBubble extends Group {
    private static final double CORNER_RADIUS = 16;
    private static final double BUBBLE_MARGIN = 10;

    private static final Font DEFAULT_TEXT_FONT = new Font(16);
    private static final Color DEFAULT_TEXT_COLOR = Color.BLACK;

    private static final Font DEFAULT_META_FONT = new Font(12);
    private static final Color DEFAULT_META_COLOR = Color.DIMGRAY;

    private Label textLabel;
    private Label metaLabel;
    private Rectangle rect;

    /**
     * Creates a new message bubble.
     *
     * @param text the main text for the bubble
     * @param textFont the font for the main text
     * @param textColor the color for the main text
     * @param metadata the metadata text for the bubble
     * @param metadataFont the font for the main text
     * @param metadataColor the color for the main text
     * @param bubbleColor the color for the bubble
     */
    public MessageBubble(String text, Font textFont, Color textColor, String metadata, Font metadataFont,
            Color metadataColor, Color bubbleColor) {
        super();

        double[] textBounds = calcTextBounds(text, textFont);
        double[] metaBounds = calcTextBounds(metadata, metadataFont);

        // 2x bubble margin for left/right of text
        double width = Math.max(textBounds[0], metaBounds[0]) + BUBBLE_MARGIN * 2;
        // 2x bubble margin for top/bottom, plus 0.5 between text and meta
        double height = textBounds[1] + BUBBLE_MARGIN * 2.5 + metaBounds[1];

        rect = new Rectangle();
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setArcWidth(CORNER_RADIUS);
        rect.setArcHeight(CORNER_RADIUS);
        rect.setFill(bubbleColor);

        textLabel = new Label(text);
        textLabel.setFont(textFont);
        textLabel.setTextFill(textColor);
        textLabel.setTranslateX(BUBBLE_MARGIN);
        textLabel.setTranslateY(BUBBLE_MARGIN);

        metaLabel = new Label(metadata);
        metaLabel.setFont(metadataFont);
        metaLabel.setTextFill(metadataColor);
        metaLabel.setTranslateX(width - BUBBLE_MARGIN - metaBounds[0]);
        metaLabel.setTranslateY(height - BUBBLE_MARGIN - metaBounds[1]);

        getChildren().addAll(rect, textLabel, metaLabel);
    }

    /**
     * Creates a new message bubble.
     *
     * @param text the main text for the bubble
     * @param metadata the metadata text for the bubble
     * @param bubbleColor the color for the bubble
     */
    public MessageBubble(String text, String metadata, Color bubbleColor) {
        this(text, DEFAULT_TEXT_FONT, DEFAULT_TEXT_COLOR, metadata, DEFAULT_META_FONT, DEFAULT_META_COLOR, bubbleColor);
    }

    /**
     * Calculates the bounds for a string rendered with the given font.
     *
     * @param text the string for which the bounds should be calculated
     * @param font the font with which the bounds should be calculated
     * @return a double[] array with two elements, containing the width and height of the bounds
     */
    private static double[] calcTextBounds(String text, Font font) {
        Text txt = new Text(text);
        txt.setFont(font);
        Bounds bounds = txt.getLayoutBounds();

        return new double[] {
            bounds.getWidth(),
            bounds.getHeight()
        };
    }
}
