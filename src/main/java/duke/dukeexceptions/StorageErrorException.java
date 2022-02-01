package duke.dukeexceptions;

import duke.responses.Response;

public class StorageErrorException extends DukeException {

    public StorageErrorException(String msg) {
        super(msg);
    }

    /**
     * Prints a error msg
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += DukeException.DIVIDER + "\n";
        uiResponse += "Invalid Storage \n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
