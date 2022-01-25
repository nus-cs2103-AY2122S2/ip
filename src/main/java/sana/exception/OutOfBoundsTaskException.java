package sana.exception;

/**
 * This class represents the sana.exception thrown when the mark index provided to Sana is out of bounds
 *
 * @author Jan
 * @version 1.0
 */
public class OutOfBoundsTaskException extends Exception {
    /** The message Sana says when encountering this sana.exception */
    private static String message = "Heyyy, I can't find this sana.task..";

    public OutOfBoundsTaskException() {
        super(OutOfBoundsTaskException.message);
    }

    public String getMessage() {
        return OutOfBoundsTaskException.message;
    }
}
