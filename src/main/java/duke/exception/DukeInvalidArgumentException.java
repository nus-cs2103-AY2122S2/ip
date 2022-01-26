package duke.exception;

import duke.ui.Ui;

public class DukeInvalidArgumentException extends DukeException {
    public DukeInvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String result = Ui.divider + "\n";
        result += "    Invalid argument(s): " + this.getMessage() + "\n";
        result += Ui.divider;
        return result;
    }
}

