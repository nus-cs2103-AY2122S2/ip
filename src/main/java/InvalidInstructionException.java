/**
 * Custom exception for Arthur.
 * Thrown when input is not a supported instruction.
 */
public class InvalidInstructionException extends Exception{
    public InvalidInstructionException() {
        super("Sorry, I don't know what that means");
    }
}
