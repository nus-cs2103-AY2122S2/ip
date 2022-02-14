package apollo.commands;

import static apollo.messages.Messages.EXIT_MESSAGE;

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
        return EXIT_MESSAGE;
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
