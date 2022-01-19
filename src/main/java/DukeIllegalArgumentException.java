public abstract class DukeIllegalArgumentException extends DukeException {
    public DukeIllegalArgumentException(String message) {
        super(message);
    }

    @Override
    public abstract String toString();
}
