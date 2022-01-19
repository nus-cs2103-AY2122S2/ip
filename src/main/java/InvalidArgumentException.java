public class InvalidArgumentException extends DukeException {
    private String message;

    public InvalidArgumentException(String message) {
        super(message);
    }

    @Override
    public String getMessage(String message) {
        return super.getMessage() + this.message;
    }
}
