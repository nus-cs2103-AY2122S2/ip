package luke.commands;

import luke.data.TaskList;

/**
 * Implements the mark command.
 */
public class MarkCommand extends UpdateCommand {

    private static final String DEFAULT_MESSAGE = "Using the force... Great! I have forced this task as done.";

    /**
     * Constructs the mark command with the specified index.
     *
     * @param index The specified index of the task to be mark as done in the task list.
     */
    public MarkCommand(int index) {
        super(index);
    }

    /**
     * Takes in a task list, marks the task at the specified index in the task list as done.
     * Returns its command result.
     *
     * @param taskList The task list to perform the action on.
     * @return The result of this command's execution.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        try {
            taskList.get(getIndex() - 1).markAsDone();
            return new CommandResult(DEFAULT_MESSAGE);
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(TASK_NOT_FOUND_MESSAGE);
        }
    }
}
