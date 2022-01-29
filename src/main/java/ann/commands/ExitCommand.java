package ann.commands;

/**
 * Represents a user command to exit from the program.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class ExitCommand extends Command{

    /**
     * Creates a new ExitCommand.
     */
    public ExitCommand() {

    }

    /**
     * Returns true if the given command is an ExitCommand and false otherwise.
     *
     * @param command the given Command.
     * @return a boolean, which indicates where command is an ExitCommand.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }

    @Override
    public void executeCommand() {
        super.setMessage("Sad to see you go :( See you again soon!");
    }
}