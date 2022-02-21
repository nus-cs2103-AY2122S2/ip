package li.zhongfu.cs2103.chatbot.types.message;

/**
 * A class representing a {@code ChatMessage}, which adds a sender and receiver to a {@code Message}
 */
public class ChatMessage extends Message {
    private Participant from;
    private Participant to;

    /**
     * Creates a new ChatMessage instance representing a message sent from Duke to the user.
     *
     * @param msg the text associated with this ChatMessage
     */
    public ChatMessage(String msg) {
        super(msg);
        this.from = Participant.getDukeParticipant();
        this.to = Participant.getUserParticipant();
    }

    /**
     * Creates a new ChatMessage instance representing a message with content {@code msg} sent from {@code from} to
     * {@code to}.
     *
     * @param msg the text associated with this ChatMessage
     * @param from the sender of this message
     * @param to the receiver of this message
     */
    public ChatMessage(String msg, Participant from, Participant to) {
        super(msg);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the {@code Participant} instance representing the sender.
     *
     * @return the {@code Participant} instance representing the sender
     */
    public Participant getFrom() {
        return from;
    }

    /**
     * Returns the {@code Participant} instance representing the receiver.
     *
     * @return the {@code Participant} instance representing the receiver
     */
    public Participant getTo() {
        return to;
    }
}

