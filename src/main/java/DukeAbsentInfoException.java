public class DukeAbsentInfoException extends DukeException {
    String message;

    DukeAbsentInfoException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return message;
    }
}