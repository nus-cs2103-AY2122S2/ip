package duke.commands;

import duke.exception.DukeException;

/**
 * Abstract class of commands.
 *
 * @param <T>
 */
public abstract class Command<T> {
    public String execute() throws DukeException, DukeException {
        return "";
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return false to not stop the bot from running
     */
    public boolean isExit() {
        return true;
    }
}
