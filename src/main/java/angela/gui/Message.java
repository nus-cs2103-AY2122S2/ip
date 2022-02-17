package angela.gui;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public abstract class Message extends HBox {

    /**
     * Loads FXML for the dialog box
     *
     * @throws IOException If an I/O error occurs
     */
    protected void loadFxml() throws IOException {}

    protected void loadText(String text) {}

    //@author khoahre123-reused
    //Reused from https://stackoverflow.com/questions/19612591/javafx-optimal-width-of-textflow-with-word-wrap
    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        double maxChildWidth = 0;
        for (Node child : getManagedChildren()) {
            double childWidth = child.getLayoutBounds().getWidth();
            maxChildWidth = Math.max(maxChildWidth, childWidth);
        }
        double insetWidth = getInsets().getLeft() + getInsets().getRight();
        double adjustedWidth = maxChildWidth + insetWidth;

        setMaxWidth(adjustedWidth);
    }
}
