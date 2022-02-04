package ann.commands;

/**
 * Represents a user command that is invalid or incorrect.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class IncorrectCommand extends Command {
    /**
     * Creates a new IncorrectCommand with the specified message.
     *
     * @param message a String which is the message to be displayed to the user.
     */
    public IncorrectCommand(String message) {
        super.setMessage(message);
    }

    /**
     * Does nothing as the command is invalid or incorrect.
     */
    @Override
    public void executeCommand() {

    }
}
