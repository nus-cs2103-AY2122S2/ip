package sana.exception;

public class MemoryNotFoundException extends Exception {
    /** The message Sana says when the message is incomplete */
    private static final String MESSAGE = "I can't find my memory file!";

    public MemoryNotFoundException() {
        super(MemoryNotFoundException.MESSAGE);
    }

    public String getMessage() {
        return MemoryNotFoundException.MESSAGE;
    }
}
