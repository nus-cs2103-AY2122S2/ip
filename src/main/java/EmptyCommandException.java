public class EmptyCommandException extends DazzException {
    public EmptyCommandException() {
        super("\tOOPS!!! You did not provide me with any command.");
    }
}
