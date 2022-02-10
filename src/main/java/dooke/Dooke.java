package dooke;

import dooke.core.InputIdentifier;
import dooke.core.InputType;
import dooke.core.IoManager;


/**
 * Dooke CLI Chatbot
 *
 * @author s7manth
 * @version 0.1
 */
public class Dooke {
    private IoManager ioManager;

    public Dooke() {
        ioManager = IoManager.getInstance();
        ioManager.start();
    }

    public String getResponse(String input) {
        return ioManager.getResponse(input);
    }

    public boolean isBye(String input) {
        return InputIdentifier.determineInputType(input).equals(InputType.BYE);
    }

    public String welcome() {
        return ioManager.getWelcomeMessage();
    }
}
