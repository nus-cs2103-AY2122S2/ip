package duke.ui;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Class handling invalid type of text
 */
public class InvalidText extends ValidText {

    public InvalidText(String text) {
        super(text);
    }

    @Override
    public Node create() {
        Text text = (Text) super.create();
        text.setFill(Color.RED);
        text.setFont(Font.font(null, FontWeight.BOLD, DEFAULT_TEXT_SIZE));
        return text;
    }
}
