package stevie.command;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;
import stevie.task.types.Task;
import stevie.undo.UndoHistory;
import stevie.undo.actions.Undo;
import stevie.undo.actions.UndoAdd;

/**
 * AddCommand executes to add a task to the task list, and save the newly updated task list.
 */
public class AddCommand extends ChangeCommand<Task> {
    private static final String message = "Got it! I have added a new task:\n";
    /**
     * Constructor for an AddCommand
     *
     * @param task task to be added
     */
    public AddCommand(Task task) {
        super(task);
    }

    /**
     * Executes the add instruction given by the user. Adds the task stored in the field to the
     * task list. Newly updated task list is saved, and ui outputs a response string to let user
     * know that AddCommand is successfully executed.
     *
     * @param tasks   task list to make changes on
     * @param storage to handle the saving of data
     * @param ui      to pass a response string for output
     * @param undoHistory handles the history of the commands executed
     * @return false to not terminate the session
     */
    @Override
    public String execute(TaskList tasks, TaskDataHandler storage, StevieUi ui, UndoHistory undoHistory) {
        tasks.add(parameter);
        undoHistory.addUndo(generateUndo());
        tasks.save(storage);
        String out = message + parameter.toString() + "\n"
                + tasks.getSize();
        ui.outputMessage(out);
        return out;
    }

    /**
     * Generate undo command for add command. Removes the last task that was
     * added.
     * @return an undo command to undo the previous add command
     */
    public Undo generateUndo() {
        return new UndoAdd();
    }
}
