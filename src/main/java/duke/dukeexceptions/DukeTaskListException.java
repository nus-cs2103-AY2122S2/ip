package duke.dukeexceptions;

import duke.responses.Response;

public class DukeTaskListException extends DukeDateExceptions {

    public DukeTaskListException(String msg) {
        super(msg);
    }

    /**
     * Prints a error msg
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += DukeException.DIVIDER + "\n";
        uiResponse += "This is not a valid index! \n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
