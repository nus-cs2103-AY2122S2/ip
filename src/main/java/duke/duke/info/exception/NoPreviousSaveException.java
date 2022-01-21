package duke.info.exception;

import duke.utils.Text;

public class NoPreviousSaveException extends DukeException {
    public NoPreviousSaveException() { super(Text.TEXT_LOADING_ERROR); }
}
