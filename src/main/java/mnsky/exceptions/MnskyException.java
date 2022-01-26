package mnsky.exceptions;

public class MnskyException extends RuntimeException {
    private String message;

    /**
     * Creates a MnskyException object.
     * @param message The message the MnskyException object should have.
     */
    public MnskyException(String message) {
        this.message = message;
    }

    /**
     * Gets the message of the exception.
     * @return The message of the exception.
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
