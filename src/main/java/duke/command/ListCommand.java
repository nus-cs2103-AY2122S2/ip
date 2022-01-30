package duke.command;

import duke.task.TaskList;

/**
 * Represents a command to list all tasks in the task list. A
 * <code>ListCommand</code> object can be created. When executing
 * the object, it will return a command feedback to print all task in
 * the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String COMMAND_FORMAT = COMMAND_WORD;

    /**
     * Returns a command feedback after the execution of the ListCommand.
     *
     * @param taskList a list of task.
     * @return a command feedback of CommandType.LIST.
     */
    @Override
    public CommandFeedback execute(TaskList taskList) {
        return new CommandFeedback(CommandType.LIST, taskList);
    }

    /**
     * Returns the command word of the ListCommand.
     *
     * @return command word.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
