package duke.info.exception;

import duke.utils.Text;

public class NoPreviousSaveException extends DukeException {

    /**
     * Constructs a NoPreviousSaveException with the loading error
     * from Text.java in the duke.utils package
     */

    public NoPreviousSaveException() { super(Text.TEXT_LOADING_ERROR); }
}
