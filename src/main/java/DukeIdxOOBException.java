public class DukeIdxOOBException extends DukeException {
    String message = "☹ OOPS!!! The index specified is out of bounds.";

    @Override
    public String toString() {
        return message;
    }
}
