package duke.dukeexceptions;

/**
 * The exception when the command entered by the user is not valid.
 */
public abstract class InvalidCommand extends DukeExceptions{
    protected InvalidCommand(String s) {
        super(s);
    }

    /**
     * Creates an invalid command when the user enters one.
     *
     * @param cmd The user input. Can be Empty.
     * @return An Invalid command.
     * @throws InvalidCommand Either MissingCommand Exception representing no command is entered or IncorrectCommand
     *                        representing commands other than specified in the Command class is entered.
     */
    public static InvalidCommand createInvalidCommand(String cmd) throws InvalidCommand {
        throw cmd.isBlank() ? new MissingCommand() : new IncorrectCommand();
    }
}

/**
 * The missing command exception that represents the user enters an empty command.
 */
final class MissingCommand extends InvalidCommand {
    MissingCommand() {
        super("Sorry, I don't see any commands!");
    }
}

/**
 * The incorrect command exception that represents the users enters a command that is not specified by Duke.
 */
final class IncorrectCommand extends InvalidCommand {
    IncorrectCommand() {
        super("Sorry! I don't undestand what are you saying!");
    }
}