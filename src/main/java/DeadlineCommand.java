public class DeadlineCommand extends AddCommand {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_FORMAT = COMMAND_WORD + " <task name> /by dd-MM-yyyy HHmm";

    public DeadlineCommand(Deadline newDeadline) {
        super(newDeadline);
    }
}
