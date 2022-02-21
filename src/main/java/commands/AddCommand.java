package commands;

import tasklist.TaskList;
import tasklist.TaskListException;
import tasks.Task;
import ui.Ui;

/**
 * Represents a user's instruction for the bot to add a new task.
 */
public class AddCommand extends Command {
    /** Represents the command word to add a Todo type of task. */
    public static final String ADD_TODO_COMMAND = "todo";
    /** Represents the command word to add a Deadline type of task. */
    public static final String ADD_DEADLINE_COMMAND = "deadline";
    /** Represents the command word to add an Event type of task. */
    public static final String ADD_EVENT_COMMAND = "event";

    private final Task taskToAdd;

    /**
     * Returns an AddCommand object that can execute a user's instruction
     * to add a given task to a task list.
     *
     * @param taskToAdd the task to be added.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the adding of a given task to the given task list and
     * provides a relevant interface for the whole process.
     *
     * @param ui       the interface to utilise for the current instruction.
     * @param taskList the tasks to operate on for the current instruction.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        try {
            taskList.add(this.taskToAdd);
            ui.showAddTask(this.taskToAdd, taskList.size());
        } catch (TaskListException ex) {
            ui.showError(ex.getMessage());
        }
    }

    /**
     * The add command does not require an exit after its execution.
     *
     * @return Always returns false to indicate that an exit is not required after execution.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
