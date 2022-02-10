package karen.command;

import karen.KarenException;
import karen.Storage;
import karen.Ui;

/**
 * To indicate invalid user inputs.
 */
public class InvalidCommand extends Command {
    private InvalidMessage invalidMessage;

    /**
     * Constructor function to override default message of InvalidCommand
     *
     * @param inputMessage custom enum message to describe invalid command
     */
    public InvalidCommand(InvalidMessage inputMessage) {
        invalidMessage = inputMessage;
    }

    /**
     * Constructor function to create InvalidCommand objects with DEFAULT_MESSAGE
     */
    public InvalidCommand() {
        invalidMessage = InvalidMessage.INVALID_DEFAULT;
    }

    /**
     * Getter function for getting message describing invalid command
     *
     * @return message that describes invalid command
     */
    public String getMessage() {
        return invalidMessage.toString();
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

    /**
     * Temp fix for mocking - to be done later
     * @return Not return an output string directly
     * @throws KarenException with message to describe invalidness of command
     */
    public String execute() throws KarenException {
        throw new KarenException(invalidMessage);
    }
}
