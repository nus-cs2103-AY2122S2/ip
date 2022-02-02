package duke.exception;

import duke.ui.Ui;

/**
 * This exception occurs when the user provides empty command or a non-existent command.
 */
public class DukeCommandException extends DukeException {
    /**
     * Instantiates this exception with an input error message, using parent constructor.
     *
     * @param message String Error message.
     */
    public DukeCommandException(String message) {
        super(message);
    }

    /**
     * Returns a String representation of this exception.
     *
     * @return String String representation of DukeCommandException.
     */
    @Override
    public String toString() {
        if (getMessage().isEmpty()) {
            return Ui.DIVIDER + "\n" + "    Please specify a command! \n" + Ui.DIVIDER;
        }
        String result = Ui.DIVIDER + "\n";
        result += "    Unknown command: " + this.getMessage() + "\n";
        result += Ui.DIVIDER;
        return result;
    }
}
