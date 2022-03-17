package duke.exception;

import duke.utils.Constants;

/**
 * Class for exception handling date format
 */
public class DateFormatException extends DukeException {
    public DateFormatException(String e) {
        super(e);
    }

    @Override
    public String getMessage() {
        return "Please write date in the forms of " + Constants.DF_LOCAL_TIME;
    }
}
