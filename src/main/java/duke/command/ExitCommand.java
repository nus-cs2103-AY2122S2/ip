package duke.command;

import duke.task.TaskList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_FORMAT = COMMAND_WORD;

    @Override
    public CommandFeedback execute(TaskList taskList) {
        return new CommandFeedback(CommandType.EXIT);
    }
}
