package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that when executed marks a designated task as done.
 */
public class MarkTaskCommand extends Command {
    private final int taskIndex;

    /**
     * Class constructor.
     *
     * @param taskIndex index of the task to be marked.
     */
    public MarkTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Invokes <code>markTask</code> method of <code>taskList</code> to mark the designated task.
     * After that, prompts <code>ui</code> to display response messages to user.
     *
     * @param ui user interface of the application.
     * @param taskList task list of the application.
     * @param storage disk storage of the application.
     * @throws DukeException when an exception is thrown in the process of executing this command.
     */
    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        taskList.markTask(taskIndex);

        ui.showMessage("Nice! I've marked this task as done: ");
        ui.showMessage(taskList.getDescriptionOfTaskAtIndex(taskIndex));
    }
}
