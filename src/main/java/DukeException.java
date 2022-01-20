public class DukeException extends Exception {

    public DukeException(String msg) {
        super("Uh-oh :( " + msg);
    }
}
