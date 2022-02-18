package duke.commands;

import duke.Storage;
import duke.TextUi;
import duke.exceptions.UndoException;
import duke.tasks.TaskList;

/**
 * Represents a command that lists tasks that have been stored in Duke.
 */
public class List extends Command {
    /**
     * Returns a success string containing the list of items that are present in the task list.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @param ui A UI object that is used to print the System's response.
     * @param storage A storage object that is able to read and write to storage file.
     * @return A string containing the list of tasks that is stored in Duke.
     */
    @Override
    public String execute(TaskList taskList, TextUi ui, Storage storage) {

        return TaskList.listTasks();
    }

    /**
     * Returns an UndoException since a list command cannot be undone.
     *
     * @param taskList A taskList containing all existing tasks in Duke.
     * @throws UndoException since the program is unable to undo a list command.
     **/
    @Override
    public String undo(TaskList taskList) throws UndoException {
        throw new UndoException("NOTHING");
    }
}
