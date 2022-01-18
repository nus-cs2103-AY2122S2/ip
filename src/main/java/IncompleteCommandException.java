public class IncompleteCommandException extends Exception {
    /**
     * The message Sana says when the message is incomplete
     */
    private static String message = "MATE, finish your sentence!";

    public IncompleteCommandException() {
        super(IncompleteCommandException.message);
    }

    public String getMessage() {
        return IncompleteCommandException.message;
    }
}
