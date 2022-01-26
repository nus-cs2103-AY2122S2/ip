package main.java.ari.exception;

public class EmptyCommandException extends DukeException {
    public EmptyCommandException() {
        super("Sorry Master, I did not catch that. Could you repeat?");
    }
}
