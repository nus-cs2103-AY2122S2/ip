package luke.commands;

import luke.data.TaskList;

public class ExitCommand extends Command {

    public static final CommandAction COMMAND_ACTION = CommandAction.EXIT;
    private static final String DEFAULT_MESSAGE = "I'll take my leave... Do you know who is my father?";

    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(DEFAULT_MESSAGE);
    }

    @Override
    public boolean isExitCmd() {
        return true;
    }
}
