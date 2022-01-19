public class UnknownException extends DukeException {
    private String message;

    public UnknownException(String message) {
        super(message);
    }

    @Override
    public String getMessage(String message) {
        return super.getMessage() + this.message;
    }
}
