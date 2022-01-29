package Duke.command;

import Duke.exception.DukeException;

import Duke.util.TaskList;
import Duke.util.Storage;
import Duke.util.Ui;

/**
 * This MarkCommand class will mark a task as done
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int taskIndex;

    /**
     * Constructor for MarkCommand with a given task number to be marked as done in the list.
     *
     * @param taskIndex 0-based index task number to be marked.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes command by marking task in TaskList.
     * @param taskList  List of tasks
     * @param ui        Ui provided
     * @param storage   Saved history
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTask(taskIndex);
        Storage.saveToFile(taskList.getCurrentList());
    }
}
