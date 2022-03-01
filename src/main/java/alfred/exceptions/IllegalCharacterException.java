package alfred.exceptions;

/**
 * Encapsulates the exception thrown when there is an illegal character in the input.
 */
public class IllegalCharacterException extends AlfredException {
    static String ERROR_MESSAGE =
            "Sorry sir, your input likely contains an invalid character";

    public IllegalCharacterException() {
        super(InvalidInputException.ERROR_MESSAGE);
    }
}