package li.zhongfu.cs2103.chatbot.types.message;

// special message type to signal the UI that it should exit
public class QuitMessage extends Message {
    public QuitMessage() {
        super("quit");
    }
}
