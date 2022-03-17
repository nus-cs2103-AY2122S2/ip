package duke.ui;

import javafx.scene.Node;
import javafx.scene.text.Text;

/**
 * Class for valid text based on the markdown requirement
 */
public class ValidText implements Markdown {
    protected static final int DEFAULT_TEXT_SIZE = 15;
    private String text;

    public ValidText(String text) {
        this.text = text;
    }

    @Override
    public Node create() {
        Text text = new Text(this.text);
        text.setWrappingWidth(180);
        text.setLineSpacing(2.0);
        return text;
    }
}
