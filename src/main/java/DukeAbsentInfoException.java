public class DukeAbsentInfoException extends DukeException {
    String message;

    DukeAbsentInfoException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}