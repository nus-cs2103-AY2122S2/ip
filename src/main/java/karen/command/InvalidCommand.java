package karen.command;

import karen.KarenException;
import karen.Storage;
import karen.Ui;

/**
 * To indicate invalid user inputs.
 */
public class InvalidCommand extends Command {
    public static final String DEFAULT_MESSAGE = "I don't understand anything - I want to speak with your manager";
    public String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    public InvalidCommand() {
        this.message = DEFAULT_MESSAGE;
    }

    @Override
    public void execute(Ui ui, Storage storage) throws KarenException {
        throw new KarenException(this.message);
    }

    public void execute() throws KarenException {
        // temp fix for mocking - to be done later
        throw new KarenException(this.message);
    }
}
