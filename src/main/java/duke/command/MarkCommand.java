package duke.command;

import java.util.List;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class MarkCommand extends Command {
    private static final String MESSAGE_MARK = "Nice! I've marked this task as done:";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";

    private static final String ERROR_EMPTY_MARK = "OOPS!!! Task to mark cannot be empty:(";
    private static final String ERROR_EMPTY_UNMARK = "OOPS!!! Task to unmark cannot be empty:(";
    private static final String ERROR_INVALID_MARK = "OOPS!!! Invalid task number, "
            + "please select a valid task to mark using the task's number";
    private static final String ERROR_INVALID_UNMARK = "OOPS!!! Invalid task number, "
            + "please select a valid task to unmark using the task's number";

    private int taskNumber;
    private boolean isMarked;

    /**
     * Constructor to the mark or unmark command.
     *
     * @param taskNumber Task number to mark or unmark
     * @param isMarked Boolean to indicate to mark or unmark the task
     * @throws DukeException If the task number given is empty or not an integer
     */
    public MarkCommand(String taskNumber, boolean isMarked) throws DukeException {
        if (taskNumber.equals("")) {
            if (isMarked) {
                throw new DukeException(ERROR_EMPTY_MARK);
            } else {
                throw new DukeException(ERROR_EMPTY_UNMARK);
            }
        }
        try {
            this.taskNumber = Integer.parseInt(taskNumber);
            this.isMarked = isMarked;
        } catch (NumberFormatException e) {
            if (isMarked) {
                throw new DukeException(ERROR_INVALID_MARK);
            } else {
                throw new DukeException(ERROR_INVALID_UNMARK);
            }
        }
    }

    /**
     * Execution of the mark command to mark/unmark the corresponding task.
     *
     * @param tasks Task list
     * @param ui UI object
     * @throws DukeException If the task number is out of range of the task list
     */
    @Override
    public String execute(List<Task> tasks, Ui ui) throws DukeException {
        if (this.taskNumber > tasks.size() || this.taskNumber <= 0) {
            if (this.isMarked) {
                throw new DukeException(ERROR_INVALID_MARK);
            } else {
                throw new DukeException(ERROR_INVALID_UNMARK);
            }
        }
        String message;
        Task thisTask = tasks.get(this.taskNumber - 1);
        if (isMarked) {
            thisTask.markAsDone();
            message = MESSAGE_MARK;
        } else {
            thisTask.markAsUndone();
            message = MESSAGE_UNMARK;
        }
        Storage.saveToFile(tasks);
        return ui.getTaskLine(thisTask, message);
    }

}
