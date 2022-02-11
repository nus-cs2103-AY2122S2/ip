package dooke;

import dooke.core.InputIdentifier;
import dooke.core.InputType;
import dooke.core.IoManager;


/**
 * Dooke CLI Chatbot
 *
 * @author s7manth
 * @version 0.2
 */
public class Dooke {
    private IoManager ioManager;

    /**
     * Constructor for the Dooke class.
     */
    public Dooke() {
        ioManager = IoManager.getInstance();
        ioManager.start();
    }

    /**
     * Wrapper method to obtain the output for a input command.
     * @param input The input command.
     * @return The resulting response.
     */
    public String getResponse(String input) {
        return ioManager.getResponse(input);
    }

    /**
     * Method to check whether the input command is bye or not.
     * @param input THe input command.
     * @return Boolean value related to the equality of the command to bye.
     */
    public boolean isBye(String input) {
        return InputIdentifier.determineInputType(input).equals(InputType.BYE);
    }

    /**
     * Method for the welcome message.
     * @return The welcome message.
     */
    public String welcome() {
        return ioManager.getWelcomeMessage();
    }
}
