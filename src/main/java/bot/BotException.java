package bot;

/**
 * Represents exceptions that are caused by a failure in the chat bot.
 */
public class BotException extends Exception {
    /**
     * Returns an exception which describes the reason for a chat bot
     * failure.
     *
     * @param description describes the reason for a chat bot failure.
     */
    public BotException(String description) {
        super(description);
    }
}
