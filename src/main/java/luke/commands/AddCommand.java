package luke.commands;

import luke.data.TaskList;
import luke.data.tasks.Task;

/**
 * Framework for commands that adds into the task list.
 */
public abstract class AddCommand extends Command {

    protected static final String DEFAULT_MESSAGE = "I have added the following task into list: \n\t%s\nnow you have %d tasks in the list.";
    private final Task task;


    /**
     * Constructs an add command with the specified task.
     * @param task The specified task to be added into the task list.
     */
    AddCommand(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return this.task;
    }


    @Override
    /**
     * Takes in a task list, add the task into the task list and returns its command result.
     * @param taskList The task list to perform the action on.
     * @return The result of this command's execution.
     */
    public CommandResult execute(TaskList taskList) {
        taskList.add(task);
        return new CommandResult(String.format(DEFAULT_MESSAGE, this.task, taskList.size()));
    }
}
