public class InvalidIndexException extends AlfredException {
    static String ERROR_MESSAGE = "Invalid input, sir. Likely due to an invalid list item.";
    InvalidIndexException() {
        super(InvalidIndexException.ERROR_MESSAGE);
    }
}