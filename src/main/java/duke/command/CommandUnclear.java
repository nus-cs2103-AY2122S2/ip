package duke.command;

import duke.Ui;
import duke.Response;

public class CommandUnclear extends Command {
    @Override
    public void execute() {
        Ui.wrapPrint(Response.RESPONSE_CANTUNDERSTAND);
    }
}
