package duke.command;

import duke.Response;
import duke.Ui;

public class CommandUnclear extends Command {
    @Override
    public void execute() {
        Ui.wrapPrint(Response.RESPONSE_CANTUNDERSTAND);
    }
}
