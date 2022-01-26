package duke.exception;

import duke.ui.Ui;

public class DukeMissingArgumentException extends DukeException {
    public DukeMissingArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String result = Ui.divider + "\n";
        result += "    We are missing the following argument: " + this.getMessage() + "\n";
        result += Ui.divider;
        return result;
    }
}
