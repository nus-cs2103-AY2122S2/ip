/**
 * This class represents the exception thrown when the mark index provided to Sana is out of bounds
 *
 * @author Jan
 * @version 1.0
 */
public class OutOfBoundsMarkException extends Exception {
    /**
     * The message Sana says when encountering this exception
     */
    private static String message = "Heyyy, I can't find this task..";

    public OutOfBoundsMarkException() {
        super(OutOfBoundsMarkException.message);
    }

    public String getMessage() {
        return OutOfBoundsMarkException.message;
    }
}
