package duke.exceptions;

import duke.ui.Ui;

public class CorruptedSaveException extends DukeException {
    public CorruptedSaveException() {
        super(Ui.CORRUPTED_SAVE_MESSAGE);
    }
}
