package kidsnd274.duke.command;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";

    public CommandResult runCommand() {
        return new CommandResult();
    }

    public static boolean isExitCommand(Command command) {
        return command instanceof ExitCommand;
    }
}
