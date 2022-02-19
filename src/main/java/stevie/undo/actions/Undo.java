package stevie.undo.actions;

import stevie.StevieUi;
import stevie.task.TaskDataHandler;
import stevie.task.TaskList;

/**
 * Undo is an interface to be implemented by undo commands. Undo commands are used to reverse
 * commands that made changes to the task list. To implement Undo, class has to override the undo method.
 */
public interface Undo {
    /**
     * Reverses the last command that made changes to the task list.
     * @param tasks list storing tasks of user
     * @param storage handles the saving of tasks in list
     * @param ui command line interface that interacts with use
     * @return response message to user
     */
    String undo(TaskList tasks, TaskDataHandler storage, StevieUi ui);
}
