package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.task.Task;
import duke.logic.TaskList;
import duke.logic.Ui;

/**
 * Represents a command that marks or unmarks a task.
 *
 * @author Peter
 */
public class MarkCommand extends Command {
    /**
     * Index of task in a list of tasks that is to be marked.
     */
    private final int index;

    /**
     * Boolean flag that determines whether to mark or unmark task.
     */
    private final boolean isMark;

    /**
     * Constructor for a task mark command.
     *
     * @param index  Index of task in a list of tasks that is to be marked.
     * @param isMark Boolean flag that determines whether to mark or unmark task.
     */
    public MarkCommand(int index, boolean isMark) {
        this.index = index;
        this.isMark = isMark;
    }

    /**
     * Marks the task located at the associated index in a given list of tasks, displays a
     * response message, and writes to a local file associated with the list of tasks.
     *
     * @param taskList List of tasks that is to be operated on.
     * @param ui       UI responsible for displaying response message.
     * @param storage  Storage responsible for reading and writing to local file.
     * @return <code>true</code> upon successful execution.
     * @throws DukeException If write to file is unsuccessful.
     */
    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        if (isMark) {
            task.markAsDone();
            ui.showMessage("TASK DONE:\n" + task);
        } else {
            task.markAsUndone();
            ui.showMessage("TASK UNDONE:\n" + task);
        }
        storage.writeToFile(taskList);
        return true;
    }
}
