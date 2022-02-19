package duke;

/**
 * Overarching command class
 */
public abstract class Command {

    /**
     * Executes command
     * @param storage storage
     * @param tasks list of tasks
     * @param ui User interface
     * @throws DukeException when an error occurs
     */
    public abstract String execute(Storage storage, TaskList tasks, Ui ui) throws DukeException;

    /**
     * Indicative of whether program should continue running
     * @return boolean value indicative of whether program should continue running
     */
    public abstract boolean isExit();
}
