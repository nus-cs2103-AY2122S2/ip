public class EmptyCommandException extends DazzException {
    public EmptyCommandException() {
        super("\t\u2639 OOPS!!! You did not provide me with any command.");
    }
}
