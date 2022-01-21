public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_EXIT = "Exiting Meep as requested ...";

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand; // instanceof returns false if it is null
    }

    @Override
    public String execute(ListTask tasks) {
        return MESSAGE_EXIT;
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
