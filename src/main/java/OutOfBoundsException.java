public class OutOfBoundsException extends DukeException {
    private String message;

    public OutOfBoundsException(String message) {
        super(message);
    }

    @Override
    public String getMessage(String message) {
        return super.getMessage() + this.message;
    }
}
