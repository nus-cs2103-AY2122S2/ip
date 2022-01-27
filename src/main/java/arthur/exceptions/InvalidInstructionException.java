package arthur.exceptions;

/**
 * Custom exception for arthur.Arthur.
 * Thrown when input is not a supported instruction.
 */
public class InvalidInstructionException extends Exception{
    public InvalidInstructionException() {
        super("Sorry, I don't know what that means");
    }

    /**
     * A constructor for a custom invalid instruction error message
     * @param str The message to be displayed
     */
    public InvalidInstructionException(String str) {
        super(str);
    }
}
