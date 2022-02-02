package duke.exception;

import duke.ui.Ui;

/**
 * This exception occurs when an invalid argument is a part of an input.
 */
public class DukeInvalidArgumentException extends DukeException {
    /**
     * Instantiates this exception with an input error message, using parent constructor.
     *
     * @param message String Error message.
     */
    public DukeInvalidArgumentException(String message) {
        super(message);
    }

    /**
     * Returns a String representation of this exception.
     *
     * @return String String representation of DukeInvalidArgumentException.
     */
    @Override
    public String toString() {
        String result = Ui.DIVIDER + "\n";
        result += "    Invalid argument(s): " + this.getMessage() + "\n";
        result += Ui.DIVIDER;
        return result;
    }
}

