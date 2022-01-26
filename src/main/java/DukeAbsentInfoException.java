public class DukeAbsentInfoException extends DukeException {

    DukeAbsentInfoException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return this.message;
    }
}