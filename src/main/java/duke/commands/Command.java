package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;

/**
 * Class that handles the different types of commands that the user keys in.
 */
public abstract class Command {
    /**
     * Returns nothing.
     * This method will be overridden in its child classes.
     * to perform different command actions.
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @throws DukeException if there are issues with executing a particular command.
     */
    public abstract String execute(TaskList taskList, TextUi ui, Storage storage) throws DukeException;

    /**
     * Returns nothing.
     * This method will overridden in the AddDeadline, AddEvent, AddTodo,
     * Delete, Mark, Unmark, Undo classes so as to undo the specific
     * actions that these classes have done.
     * @param taskList A taskList containing all existing tasks in Duke.
     * @throws DukeException if there are issues with undoing a particular command.
     */
    public abstract String undo(TaskList taskList) throws DukeException;
}
