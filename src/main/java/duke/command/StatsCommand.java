package duke.command;

import duke.task.TaskList;

public class StatsCommand extends Command {
    public static final String COMMAND_WORD = "stats";
    public static final String COMMAND_FORMAT = COMMAND_WORD;

    /**
     * Returns a command feedback after the execution of the Command.
     *
     * @param taskList a list of task.
     * @return a command feedback of CommandType.STATS.
     */
    @Override
    public CommandFeedback execute(TaskList taskList) {
        return new CommandFeedback(CommandType.STATS, taskList);
    }

    /**
     * Returns the command word of the StatsCommand.
     *
     * @return command word.
     */
    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }
}
