package li.zhongfu.cs2103.chatbot.types.message;

/**
 * A special type of {@code Message} used to signal to the frontend that it should exit.
 */
public class QuitMessage extends Message {
    /**
     * Creates a new {@code QuitMessage} instance.
     */
    public QuitMessage() {
        super("quit");
    }
}
