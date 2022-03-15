package duke.command;

import duke.dukeexception.DukeException;
/**
 * Represents the command to be executed by duke.
 */
public abstract class Command {
    /**
     * Executes what the command specifies.
     */
    public abstract void execute() throws DukeException;
    public abstract String getResponse();

}
