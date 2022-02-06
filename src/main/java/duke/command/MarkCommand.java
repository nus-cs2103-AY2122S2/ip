package duke.command;

import duke.exception.DukeException;
import duke.ui.ImageType;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command which, when executed, marks or unmarks a Task from the given TaskList instance.
 */
public class MarkCommand extends Command {
    private static final boolean IS_EXIT = false;
    private int taskIndex;
    private boolean isDone;

    /**
     * Creates a new MarkCommand instance with the initialized index and action.
     *
     * @param taskIndex Zero based index of the Task to be marked or unmarked.
     * @param isDone    The intended state of the Task after execution of Command.
     *                  true if the intended action is to mark the task, false if the
     *                  intended action is to unmark the task.
     */
    public MarkCommand(int taskIndex, boolean isDone) {
        super(IS_EXIT);
        this.taskIndex = taskIndex;
        this.isDone = isDone;
    }

    /**
     * Marks or unmarks the Task object in the TaskList and displays the message on Ui.
     * Displays the error message on Ui if the given Task cannot be marked or unmarked successfully.
     *
     * @param tasks The TaskList instance containing the Task object to be marked or unmarked.
     * @param ui    The Ui object used for displaying messages.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert ui.hasEmptyMessage() : "Ui has leftover message from previous tasks";
        tasks.markTask(taskIndex, isDone);
        String message;
        if (isDone) {
            message = Ui.MARK_MESSAGE;
        } else {
            message = String.format(Ui.UNMARK_MESSAGE);
        }
        ui.appendMessage(message);
        ui.appendBorder();
        ui.appendMessage(tasks.getTask(taskIndex).toString());
        if (isDone) {
            ui.setRespondImage(ImageType.MARK);
        } else {
            ui.setRespondImage(ImageType.UNMARK);
        }
    }
}
