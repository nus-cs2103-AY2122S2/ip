public class DukeException {

    String message;

    DukeException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}