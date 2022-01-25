package duke.commands;

import duke.DukeList;
import duke.UI;
import duke.exceptions.InvalidTaskException;

public abstract class Command {
    public boolean isExit() { return false; }

    /**
     * Function to execute the command.
     *
     * @param dukeList dukelist object
     * @param ui ui object
     * @throws InvalidTaskException invalid task
     */
    public abstract void execute(DukeList dukeList, UI ui) throws InvalidTaskException;
}
