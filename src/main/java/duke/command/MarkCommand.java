package duke.command;

import java.util.List;
import duke.exception.DukeException;
import duke.task.Task;
import duke.Ui;

import duke.Storage;

public class MarkCommand extends Command {
    private static final String MESSAGE_MARK = "Nice! I've marked this task as done:";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";

    private static final String ERROR_EMPTY_MARK = "OOPS!!! Task to mark cannot be empty:(";
    private static final String ERROR_EMPTY_UNMARK = "OOPS!!! Task to unmark cannot be empty:(";
    private static final String ERROR_INVALID_MARK = "OOPS!!! Invalid task number, " +
            "please select a valid task to mark using the task's number";
    private static final String ERROR_INVALID_UNMARK = "OOPS!!! Invalid task number, " +
            "please select a valid task to unmark using the task's number";

    private int taskNumber;
    private boolean toMark;

    public MarkCommand(String taskNumber, boolean toMark) throws DukeException {
        if (taskNumber.equals("")) {
            if (toMark) {
                throw new DukeException(ERROR_EMPTY_MARK);
            } else {
                throw new DukeException(ERROR_EMPTY_UNMARK);
            }
        }
        try {
            this.taskNumber = Integer.parseInt(taskNumber);
            this.toMark = toMark;
        } catch (NumberFormatException e) {
            if (toMark) {
                throw new DukeException(ERROR_INVALID_MARK);
            } else {
                throw new DukeException(ERROR_INVALID_UNMARK);
            }
        }
    }

    @Override
    public void execute(List<Task> tasks, Ui ui) throws DukeException {
        if (this.taskNumber > tasks.size() || this.taskNumber <= 0) {
            if (this.toMark) {
                throw new DukeException(ERROR_INVALID_MARK);
            } else {
                throw new DukeException(ERROR_INVALID_UNMARK);
            }
        }
        Task thisTask = tasks.get(this.taskNumber - 1);
        if (toMark) {
            thisTask.markAsDone();
            ui.printContent(ui.taskLine(thisTask, MESSAGE_MARK));
        } else {
            thisTask.markAsUndone();
            ui.printContent(ui.taskLine(thisTask, MESSAGE_UNMARK));
        }
        Storage.saveToFile(tasks);
    }

}
