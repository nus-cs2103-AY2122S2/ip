package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.ImageType;
import duke.ui.Ui;
import duke.utils.Priority;

public class ChangePriorityCommand extends Command {
    private static final boolean IS_EXIT = false;
    private int taskIndex;
    private Priority newPriority;

    /**
     * Creates a new ChangePriorityCommand instance with the initialized index and action.
     *
     * @param taskIndex Zero based index of the Task to have its priority changed.
     * @param newPriority The new priority level of the Task.
     */
    public ChangePriorityCommand(int taskIndex, Priority newPriority) {
        super(IS_EXIT);
        this.taskIndex = taskIndex;
        this.newPriority = newPriority;
    }

    /**
     * Changes the priority of the Task object in the TaskList and displays the message on Ui.
     * Displays the error message on Ui if priority of the given Task cannot be changed successfully.
     *
     * @param tasks The TaskList instance containing the Task object to be marked or unmarked.
     * @param ui    The Ui object used for displaying messages.
     * @throws DukeException If the index of the Task is out of range.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert ui.hasEmptyMessage() : "Ui has leftover message from previous tasks";
        tasks.setPriority(taskIndex, newPriority);
        ui.appendMessage(Ui.CHANGE_PRIORITY_MESSAGE);
        ui.appendBorder();
        ui.appendMessage(tasks.getTask(taskIndex).toString());
        ui.setRespondImage(ImageType.GENERAL);
    }
}
