package duke.command;

import duke.logic.DukeException;
import duke.logic.Storage;
import duke.logic.TaskList;
import duke.logic.TaskStack;
import duke.logic.Ui;
import duke.task.Task;

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
    private final boolean isMarkCommand;

    /**
     * Constructor for a task mark command.
     *
     * @param index         Index of task in a list of tasks that is to be marked.
     * @param isMarkCommand Boolean flag that determines whether to mark or unmark task.
     */
    public MarkCommand(int index, boolean isMarkCommand) {
        this.index = index;
        this.isMarkCommand = isMarkCommand;
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
    public String execute(TaskList taskList, Ui ui, Storage storage, TaskStack taskStack) throws DukeException {
        Task task;
        String output;

        if (taskList.isValidIndex(index)) {
            task = taskList.get(index);
        } else {
            throw new DukeException("INVALID INDEX");
        }

        TaskList copiedTaskList = new TaskList();
        copiedTaskList.copy(taskList);
        taskStack.push(copiedTaskList);

        if (isMarkCommand) {
            task.markAsDone();
            output = "TASK DONE:\n" + task;
        } else {
            task.markAsUndone();
            output = "TASK UNDONE:\n" + task;
        }
        storage.writeToFile(taskList);
        ui.showMessage(output);
        return output;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }
}
