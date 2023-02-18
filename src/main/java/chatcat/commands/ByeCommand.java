package chatcat.commands;

import chatcat.util.OutputMessage;

/**
 * The default ByeCommand class.
 */
public class ByeCommand {

    /**
     * Checks if inputs equals to String "bye".
     *
     * @param command input command.
     * @return true if command is "bye", else returns false.
     */
    public static boolean isBye(String command) {
        return command.equals("bye");
    }

    /**
     * Returns exit string.
     *
     * @return returns exit string.
     * @see OutputMessage
     */
    @Override
    public String toString() {
        return OutputMessage.byeMessage();
    }
}
