package stevie.command;

import stevie.StevieUi;
import stevie.exception.StevieException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

/**
 * MarkCommand is used to either mark a task in the task list as done or not done.
 */
public class MarkCommand extends Command {
    /**
     * Set markDone to true to mark the task as done, else task is mark as undone
     */
    private final boolean isDone;

    /**
     * The index of the task to be deleted
     */
    private final int taskIdx;

    /**
     * Constructor for MarkDone
     *
     * @param mark true to mark a task as done, else to task is mark as undone
     * @param idx  the index of the task in the task list
     */
    public MarkCommand(boolean mark, int idx) {
        isDone = mark;
        taskIdx = idx;
    }

    /**
     * Tries to mark a task as done/undone based on the boolean value in the markDone field.
     * If successful, save the updated state of the task list. Ui outputs a response string to
     * let user know if the command is successfully executed.
     *
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @return false to not terminate the session
     */
    @Override
    public String execute(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        String out;
        try {
            if (isDone) {
                out = tasks.markDone(taskIdx);
            } else {
                out = tasks.markUndone(taskIdx);
            }
        } catch (StevieException ex) {
            ui.outputMessage(ex.getMessage());
            return ex.getMessage();
        }
        tasks.save(storage);
        ui.outputMessage(out);
        return out;
    }
}
