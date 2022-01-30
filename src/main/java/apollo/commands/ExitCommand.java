package apollo.commands;

/**
 * Signals for an exit.
 * Extends {@code Command} superclass.
 */
public class ExitCommand extends Command {

    /**
     * Sends farewell message before exiting program.
     *
     * @return Message for successful execution.
     */
    @Override
    public String execute() {
        return "See you next time. \nI am always available when you need me. ";
    }

    /**
     * Checks for signal to exit program.
     *
     * @param command To check for exit signal.
     * @return Boolean if exiting.
     */
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
