package duke.command;

import duke.Response;
import duke.Ui;

public class CommandUnclear extends Command {
    @Override
    public String execute() {
        return Response.RESPONSE_CANTUNDERSTAND;
    }
}
