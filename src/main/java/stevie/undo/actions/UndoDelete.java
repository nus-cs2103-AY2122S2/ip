package stevie.undo.actions;

import stevie.StevieUi;
import stevie.exception.TaskException;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;
import stevie.task.types.Task;

public class UndoDelete implements Undo {
    private static final String message = "Undo previous delete command!";
    private final Task task;
    private final int idx;

    /**
     * Constructor for UndoDelete. Takes in a valid index, as well as a task to be
     * added to task list
     * @param task task to be added
     * @param idx index to be added at
     */
    public UndoDelete(int idx, Task task) {
        this.task = task;
        this.idx = idx;
    }
    @Override
    public String undo(TaskList tasks, TaskDataHandler storage, StevieUi ui) {
        try {
            tasks.addAtIndex(idx, task);
        } catch (TaskException ex) {
            ui.outputMessage(ex.getMessage());
            return ex.getMessage();
        }
        tasks.save(storage);
        ui.outputMessage(message);
        return message;
    }
}
