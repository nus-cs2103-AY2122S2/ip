package arthur.exceptions;

/**
 * Custom exception for arthur.Arthur.
 * Thrown when input is not a supported instruction.
 */
public class InvalidInstructionException extends Exception {
    private static final String UNKNOWN_INSTRUCTION_MESSAGE = "Sorry, I don't know what that means";

    /**
     * Creates the exception with the default message.
     */
    public InvalidInstructionException() {
        super(UNKNOWN_INSTRUCTION_MESSAGE);
    }

    /**
     * Creates the exception with the provided invalid instruction error message.
     *
     * @param str The message to be displayed.
     */
    public InvalidInstructionException(String str) {
        super(str);
    }
}
