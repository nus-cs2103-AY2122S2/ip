/**
 * This class represents the exception thrown by Sana when she receives
 * an unknown command
 *
 * @author Jan
 * @version 1.0
 */
public class UnknownCommandException extends Exception{
    /**
     * The message Sana says when she receives an unknown command
     */
    private static String message = "Oy! I don't know what you're saying!";

    public UnknownCommandException() {
        super(UnknownCommandException.message);
    }

    /**
     * The message Sana says when she receives an unknown command
     *
     * @return  message
     */
    public String getMessage() {
        return UnknownCommandException.message;
    }
}
