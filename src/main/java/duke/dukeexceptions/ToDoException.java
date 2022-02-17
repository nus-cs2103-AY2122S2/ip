package duke.dukeexceptions;

import duke.responses.Response;

public class ToDoException extends DukeException {

    public ToDoException(String msg) {
        super(msg);
    }

    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += DukeException.DIVIDER + "\n";
        uiResponse += "OOPS!!! The description of a todo cannot be empty \n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
