package duke.command;

import duke.Over;
import duke.Response;
import duke.Ui;

/**
 * Represents the 'bye' command that shuts off Duke.
 */
public class CommandBye extends Command {
    Over over;

    /**
     * Creates a new CommandBye instance.
     * @param over the boolean representation that kills Duke, used in the while loop of main logic.
     */
    public CommandBye(Over over) {
        this.over = over;
    }

    /**
     * Execution of this command.
     */
    @Override
    public void execute() {
        Ui.wrapPrint(Response.RESPONSE_GOODBYE);
        over.setOver();
    }
}
