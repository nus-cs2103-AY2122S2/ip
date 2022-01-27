package duke.command;

import java.util.List;
import duke.exception.DukeException;
import duke.task.Task;
import duke.UI;

import duke.Storage;

public class MarkCommand extends Command{
    private static final String MESSAGE_MARK = "Nice! I've marked this task as done:";
    private static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:";

    private static final String ERROR_EMPTY_MARK = "OOPS!!! Task to mark cannot be empty:(";
    private static final String ERROR_EMPTY_UNMARK = "OOPS!!! Task to unmark cannot be empty:(";
    private static final String ERROR_INVALID_MARK = "OOPS!!! Invalid task number, please select a valid task to mark using the task's number";
    private static final String ERROR_INVALID_UNMARK = "OOPS!!! Invalid task number, please select a valid task to unmark using the task's number";

    private int taskNumber;
    private boolean mark;

    /**
     * Constructor to the mark or unmark command.
     *
     * @param taskNumber Task number to mark or unmark
     * @param mark Boolean to indicate to mark or unmark the task
     * @throws DukeException If the task number given is empty or not an integer
     */
    public MarkCommand(String taskNumber, boolean mark) throws DukeException{
        if (taskNumber.equals("")){
            if (mark)
                throw new DukeException(ERROR_EMPTY_MARK);
            else
                throw new DukeException(ERROR_EMPTY_UNMARK);
        }
        try{
            this.taskNumber = Integer.parseInt(taskNumber);
            this.mark = mark;
        } catch (NumberFormatException e){
            if (mark)
                throw new DukeException(ERROR_INVALID_MARK);
            else
                throw new DukeException(ERROR_INVALID_UNMARK);
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
    public void execute(List<Task> tasks, UI ui) throws DukeException{
        if (this.taskNumber > tasks.size() || this.taskNumber <= 0){
            if (this.mark)
                throw new DukeException(ERROR_INVALID_MARK);
            else
                throw new DukeException(ERROR_INVALID_UNMARK);
        }
        Task thisTask = tasks.get(this.taskNumber-1);
        if (mark){
            thisTask.markAsDone();
            ui.printContent(ui.taskLine(thisTask, MESSAGE_MARK));
        } else{
            thisTask.markAsUndone();
            ui.printContent(ui.taskLine(thisTask, MESSAGE_UNMARK));
        }
        Storage.saveToFile(tasks);
    }

}
