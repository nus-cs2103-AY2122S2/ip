package luke.commands;

import luke.data.tasks.Deadline;

public class DeadlineCommand extends AddCommand {
    public static final CommandAction COMMAND_ACTION = CommandAction.DEADLINE;

    public DeadlineCommand(Deadline deadline) {
        super(deadline);
    }
}
