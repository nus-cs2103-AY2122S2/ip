package duke.dukeexceptions;


import duke.responses.Response;

public class ForeignException extends DukeException {

    public ForeignException(String msg) {
        super(msg);
    }

    /**
     * Prints a error msg
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += DukeException.DIVIDER + "\n";
        uiResponse += "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
