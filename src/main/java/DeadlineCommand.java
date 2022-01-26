public class DeadlineCommand extends AddCommand {
    public static final CommandAction COMMAND_ACTION = CommandAction.DEADLINE;

    DeadlineCommand(Deadline deadline) {
        super(deadline);
    }
}
