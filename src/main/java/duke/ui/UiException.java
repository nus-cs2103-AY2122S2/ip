package duke.ui;

import java.io.IOException;

/**
 * Represents exceptions occurring in UI.
 */
class UiException extends IOException {


    /**
     * Constructs an <code>ui.UiException</code> with the specified message.
     *
     * @param message The message that describes the exception.
     */
    UiException(String message) {
        super(message);
    }
}
