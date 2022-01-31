package chatcat.chatcatexception;

/**
 * Exception handling for ChatCat
 */
public class ChatCatException extends Exception {

    /**
     * Returns a {@code ChatCatException} with the given message.
     */
    public ChatCatException(String str) {
        super(str);
    }
}