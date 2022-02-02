package duke.commands;

import duke.DukeList;
import duke.UI;
import duke.exceptions.InvalidTaskException;

public abstract class Command {
    /**
     * Checks if the command is a terminating one.
     *
     * @return boolean whether to terminate
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Function to execute the command.
     *
     * @param dukeList dukeList object
     * @param ui ui object
     * @throws InvalidTaskException invalid task
     */
    public abstract void execute(DukeList dukeList, UI ui) throws InvalidTaskException;
}
