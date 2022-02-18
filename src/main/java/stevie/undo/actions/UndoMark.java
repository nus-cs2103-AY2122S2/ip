package stevie.undo.actions;

import stevie.StevieUi;
import stevie.exception.TaskException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

/**
 * UndoMark reverses the previous mark command that by reversing the "mark" state of the task.
 */
public class UndoMark implements Undo {
    private static final String message = "Undo previous mark command!";
    private final int idx;
    private final boolean isDone;

    /**
     * Constructor for UndoMark. Reverse previous mark command. Tasks in a
     * valid index for task list, and a boolean that marks or unmarks task.
     * @param idx index of task
     * @param isDone status of task
     */
    public UndoMark(int idx, boolean isDone) {
        this.idx = idx;
        this.isDone = isDone;
    }

    /**
     * Reverses the mark of the task that was previously edited by the last mark command.
     * @param tasks list storing tasks of user
     * @param storage handles the saving of tasks in list
     * @param ui command line interface that interacts with use
     * @return response message informing user that mark command has been undone
     */
    @Override
    public String undo(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        try {
            tasks.mark(idx, isDone);
        } catch (TaskException ex) {
            ui.outputMessage(ex.getMessage());
            return ex.getMessage();
        }
        tasks.save(storage);
        ui.outputMessage(message);
        return message;
    }
}
