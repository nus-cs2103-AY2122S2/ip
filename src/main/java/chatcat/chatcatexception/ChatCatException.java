package chatcat.chatcatexception;

/**
 * Exception handling for ChatCat
 */
public class ChatCatException extends Exception {
    static final String error = "OOPS!!! I'm sorry, but I don't know what that means";

    /**
     * Returns a exception {@code ChatCatException} with the given message.
     */
    public ChatCatException() {
        super(error);
    }

    /**
     * Returns a error message as a String.
     *
     * @return returns a error message as a String.
     */
    @Override
    public String toString() {
        return "OOPS!!! I'm sorry, but I don't know what that means";
    }
}