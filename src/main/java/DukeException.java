public class DukeException extends RuntimeException {

    public DukeException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.getMessage();
    }
}
