package sana.exception;

/**
 * This class represents the sana.exception thrown when the mark index provided to Sana is out of bounds
 *
 * @author Jan
 * @version 1.0
 */
public class OutOfBoundsTaskException extends SanaException {
    /** The message Sana says when encountering this sana.exception */
    private static final String MESSAGE = "Heyyy, I can't find this sana.task..";

    public OutOfBoundsTaskException() {
        super(OutOfBoundsTaskException.MESSAGE);
    }

    /**
     * The message Sana says when the index provided is out of bounds
     *
     * @return  message
     */
    public String getMessage() {
        return OutOfBoundsTaskException.MESSAGE;
    }
}
