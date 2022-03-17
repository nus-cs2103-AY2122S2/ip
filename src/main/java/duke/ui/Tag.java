package duke.ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class for tags that help markdown
 */
public class Tag implements Markdown {
    public static final String START_PATTERN = "<tag>";
    public static final String END_PATTERN = "</tag>";
    private static final String DEFAULT_TAG_COLOR = "#ffffff";
    private static final int DEFAULT_TEXT_SIZE = 15;
    private String tagStringContent;

    public Tag(String tagStringContent) {
        this.tagStringContent = tagStringContent;
    }

    @Override
    public Node create() {
        Label tag = new Label(this.tagStringContent);
        tag.setStyle("-fx-background-color: " + DEFAULT_TAG_COLOR + "; "
                + "-fx-font-weight: bold; "
                + "-fx-border-radius: 2, 2, 2, 2;"
                + "-fx-background-radius: 2, 2, 2, 2; ");
        tag.setTextFill(Color.web("#ffffff"));
        tag.setFont(new Font(DEFAULT_TEXT_SIZE));
        tag.setPadding(new Insets(2, 2, 2, 2));
        return tag;
    }
}