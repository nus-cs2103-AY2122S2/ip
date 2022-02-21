package li.zhongfu.cs2103.chatbot.types.message;

/**
 * A class representing an abstract message, e.g. between two parties.
 */
public abstract class Message {
    private String msg;

    /**
     * Creates a new instance encapsulating a message.
     *
     * @param msg the content of this message
     */
    protected Message(String msg) {
        this.msg = msg;
    }

    /**
     * Returns the content of this message.
     *
     * @return the content of this message
     */
    public String getMessage() {
        return msg;
    }
}
