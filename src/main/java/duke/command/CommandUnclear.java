package duke.command;

import duke.Response;

public class CommandUnclear extends Command {
    private String response = Response.RESPONSE_CANTUNDERSTAND;

    @Override
    public void execute() {
    }

    @Override
    public String getResponse() {
        return response;
    }
}
