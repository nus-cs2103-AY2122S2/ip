package luke.commands;

import luke.data.TaskList;

/**
 * Implements the exit command.
 */
public class ExitCommand extends Command {

    private static final String DEFAULT_MESSAGE = "I'll take my leave... Do you know who is my father?";

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(DEFAULT_MESSAGE, true);
    }

}
