package chatbot.exception;

public class ChatBotException extends Exception {

    public ChatBotException(String errorMessage) {
        super(errorMessage);
    }

    public ChatBotException() {
        super(
            "Sorry traveller! I can't quite understand what you mean by that!"
        );
    }
}
