package chatbot.exception;


/**
 * Represents the exception used by ChatBot for error handling.
 */
public class ChatBotException extends Exception {

    /**
     * Instantiates a new Chat bot exception.
     *
     * @param errorMessage The error message.
     */
    public ChatBotException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Instantiates a new Chat bot exception with a default error message.
     */
    public ChatBotException() {
        super(
                "Sorry traveller! I can't quite understand what you mean by that!"
        );
    }
}
