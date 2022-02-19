package stevie.command;

import stevie.StevieUi;
import stevie.exception.TaskException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;
import stevie.task.types.Task;
import stevie.undo.UndoHistory;
import stevie.undo.actions.Undo;
import stevie.undo.actions.UndoMark;

/**
 * MarkCommand is used to either mark a task in the task list as done or not done.
 */
public class MarkCommand extends ChangeCommand<Integer> {
    private static final String message = "This task is marked as ";
    /**
     * Set markDone to true to mark the task as done, else task is mark as undone
     */
    private final boolean isDone;

    /**
     * Constructor for MarkDone
     *
     * @param mark true to mark a task as done, else to task is mark as undone
     * @param idx  the index of the task in the task list
     */
    public MarkCommand(boolean mark, int idx) {
        super(idx);
        isDone = mark;
    }

    private String getMessage() {
        return message + (isDone ? "done" : "undone") + ":\n";
    }

    /**
     * Tries to mark a task as done/undone based on the boolean value in the markDone field.
     * If successful, save the updated state of the task list. Ui outputs a response string to
     * let user know if the command is successfully executed.
     *
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @param undoHistory handles the history of the commands executed
     * @return false to not terminate the session
     */
    @Override
    public String execute(TaskList tasks, TaskDataHandler storage, StevieUi ui, UndoHistory undoHistory) {
        Task task;
        try {
            task = tasks.mark(parameter, isDone);
        } catch (TaskException ex) {
            ui.outputMessage(ex.getMessage());
            return ex.getMessage();
        }
        undoHistory.addUndo(generateUndo(parameter));
        String out = getMessage() + task.toString();
        tasks.save(storage);
        ui.outputMessage(out);
        return out;
    }

    /**
     * Generate undo command for the mark/unmark command.
     * @param idx index of the task that was edited
     * @return undo command that reverses mark/unmark command
     */
    public Undo generateUndo(int idx) {
        return new UndoMark(idx, !isDone);
    }
}
