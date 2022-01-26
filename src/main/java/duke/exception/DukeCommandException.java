package duke.exception;

import duke.ui.Ui;

public class DukeCommandException extends DukeException {
    public DukeCommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        if (getMessage().isEmpty()) {
            return Ui.divider + "\n" + "    Please specify a command! \n" + Ui.divider;
        }
        String result = Ui.divider + "\n";
        result += "    Unknown command: " + this.getMessage() + "\n";
        result += Ui.divider;
        return result;
    }
}
