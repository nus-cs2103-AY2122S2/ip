package duke.exceptions;

import javafx.scene.control.Label;

/**
 * A generic exception thrown by Duke.
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

    public Label getLabel() {
        return new Label(super.getMessage());
    }
}
