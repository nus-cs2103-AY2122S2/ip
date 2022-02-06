package karen.command;

import karen.KarenException;
import karen.Storage;
import karen.Ui;

/**
 * To indicate invalid user inputs.
 */
public class InvalidCommand extends Command {
    public static final String DEFAULT_MESSAGE = "I don't understand anything - I want to speak with your manager";
    private String invalidMessage;

    /**
     * Constructor function to override default message of InvalidCommand
     *
     * @param message custom message to describe invalidity
     */
    public InvalidCommand(String message) {
        invalidMessage = message;
    }

    /**
     * Constructor function to create InvalidCommand objects with DEFAULT_MESSAGE
     */
    public InvalidCommand() {
        invalidMessage = DEFAULT_MESSAGE;
    }

    /**
     * Getter function for getting message describing invalid command
     *
     * @return message that describes invalid command
     */
    public String getMessage() {
        return invalidMessage;
    }

    /**
     * Throws exception to signal fault input from user.
     *
     * @param ui To control outputs related to execution
     * @param storage To access and modify Tasks stored in Storage
     * @return Does not return an output string directly
     * @throws KarenException with message to describe invalidness of command
     */
    @Override
    public String execute(Ui ui, Storage storage) throws KarenException {
        throw new KarenException(invalidMessage);
    }

    public String execute() throws KarenException {
        // temp fix for mocking - to be done later
        throw new KarenException(invalidMessage);
    }
}
