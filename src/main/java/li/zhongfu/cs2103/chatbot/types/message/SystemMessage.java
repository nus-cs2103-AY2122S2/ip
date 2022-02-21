package li.zhongfu.cs2103.chatbot.types.message;

/**
 * A type of {@code Message} that indicates that it is a system message, i.e. not a normal chat message between two
 * parties.
 */
public class SystemMessage extends Message {
    /**
     * Creates a new {@code SystemMessage} instance with the given message.
     *
     * @param msg the content of the message
     */
    public SystemMessage(String msg) {
        super(msg);
    }
}
