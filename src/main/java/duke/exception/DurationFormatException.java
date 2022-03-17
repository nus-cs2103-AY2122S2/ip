package duke.exception;

import duke.utils.Constants;

/**
 * Class for handling the duration format
 */
public class DurationFormatException extends DukeException {
    public DurationEnumFormat enumFormat;

    public DurationFormatException(String errorMessage, DurationEnumFormat enumFormat) {
        super(errorMessage);
        this.enumFormat = enumFormat;
    }

    @Override
    public String getMessage() {
        switch (this.enumFormat) {
            case WRONG_INTERVAL_DURATION:
                return "The start time is after the end time!";
            case WRONG_FORMAT_DURATION:
                return "Please write in the forms of "
                        + Constants.DF_LOCAL_TIME + "-" + Constants.DF_LOCAL_TIME
                        + " or yyyy-mm-dd hh:mm-hh:mm";
            default:
                return "others";
        }
    }
}
