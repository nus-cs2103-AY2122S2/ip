package li.zhongfu.cs2103.chatbot.types.message;

public abstract class Message {
    private String msg;

    public Message(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }
}
