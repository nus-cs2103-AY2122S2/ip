package mnsky.exceptions;

public class MnskyException extends RuntimeException {
    private String message;

    public MnskyException(String message) {
        this.message = message;
    }
    /**
     * Gets the message of the exception.
     * @return The message of the exception.
     */
    @Override
    public String getMessage() {
        return message;
    }
}
