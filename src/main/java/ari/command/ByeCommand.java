package ari.command;

/**
 * Says goodbye to user
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String BYE_MESSAGE = "Have a nice day Master";

    @Override
    public String execute() {
        // Print out goodbye message
        return ByeCommand.BYE_MESSAGE;
    }

    /**
     * Returns whether command is an instanceof ByeCommand
     *
     * @param command command that is ready to be executed
     * @return if command is an instanceof ByeCommand
     */
    public static boolean isGoodBye(Command command) {
        return command instanceof ByeCommand;
    }

}
