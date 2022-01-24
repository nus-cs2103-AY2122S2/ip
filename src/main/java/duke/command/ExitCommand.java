package duke.command;

import duke.task.TaskList;

/**
 * Represents a command to exit the program. An <code>ExitCommand</code>
 * object can be created. When executing the object, it will return a
 * command feedback to exit the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_FORMAT = COMMAND_WORD;

    /**
     * Returns a command feedback after the execution of the ExitCommand.
     *
     * @param taskList a list of task.
     * @return a command feedback of CommandType.Exit.
     */
    @Override
    public CommandFeedback execute(TaskList taskList) {
        return new CommandFeedback(CommandType.EXIT);
    }

    /**
     * Returns the command word of the ExitCommand.
     *
     * @return command word.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
