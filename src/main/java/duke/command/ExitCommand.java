package duke.command;

/**
 * A class representing the exit command. The program quits when this command is executed
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public CommandResult runCommand() {
        return new CommandResult();
    }

    public static boolean isExitCommand(Command command) {
        return command instanceof ExitCommand;
    }
}
