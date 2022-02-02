package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Abstract class from which all commands inherit from
 */
public abstract class Command {
    public static final String INDENT = "     ";

    /**
     * Abstract method for the execution of command
     *
     * @param tasks   tasks list to be modified
     * @param ui      to help with printing of messages
     * @param storage To deal with saving of task list
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Abstract method to see if command ends the main program loop
     *
     * @return true if it ends main program
     */
    public abstract boolean endsProgram();

    /**
     * Abstract method to get the modified task list after command execution
     *
     * @return TaskList
     */
    public abstract TaskList getList();
}
