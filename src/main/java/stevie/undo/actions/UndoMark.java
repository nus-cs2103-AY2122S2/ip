package stevie.undo.actions;

import stevie.StevieUi;
import stevie.exception.TaskException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

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
