package commands;

import tasklist.TaskList;
import tasklist.TaskListException;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to find a set of tasks by a given keyword.
 */
public class FindCommand extends Command {
    /** Represents the command word to find a set of tasks by a given keyword. */
    public static final String COMMAND = "find";

    private final String keyword;

    /**
     * Returns a FindCommand object that can execute a user's instruction
     * to find a set of tasks from the task list by a given keyword.
     *
     * @param keyword the criteria to find by in the task list.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the finding of a set of tasks from the task list by the
     * given keyword and provides a relevant interface for the whole process.
     *
     * @param ui       the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            ui.showFindTasks(taskList.find(this.keyword));
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    /**
     * The find command does not require an exit after its execution.
     *
     * @return Always returns false to indicate that an exit is not required after execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
