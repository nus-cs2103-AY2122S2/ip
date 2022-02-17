package duke.dukeexceptions;

import duke.Duke;
import duke.responses.Response;

/**
 * Duke exceptions that catch DateTime related errors
 */
public class DukeDateExceptions extends DukeException {

    /**
     * Constructs the DukeDateExceptions exception
     * @param msg
     */
    public DukeDateExceptions(String msg) {
        super(msg);
    }

    /**
     * Prints a error msg
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += DukeException.DIVIDER + "\n";
        uiResponse += "Please provide a valid date time format \n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
