package li.zhongfu.cs2103.chatbot.types.message;

public class ChatMessage extends Message {
    private Participant from;
    private Participant to;

    public ChatMessage(String msg) {
        super(msg);
        this.from = Participant.getDukeParticipant();
        this.to = Participant.getUserParticipant();
    }

    public ChatMessage(String msg, Participant from, Participant to) {
        super(msg);
        this.from = from;
        this.to = to;
    }

    public Participant getFrom() {
        return from;
    }

    public Participant getTo() {
        return to;
    }
}

