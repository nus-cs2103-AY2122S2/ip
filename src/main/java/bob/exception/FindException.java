package bob.exception;

public class FindException extends BobException {
    public FindException() {
        super("You need to give me something to find...");
    }
}
