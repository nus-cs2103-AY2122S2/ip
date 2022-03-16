package duke.dukeexception;

public class DukeNoSeparatorException extends DukeException {
    public DukeNoSeparatorException(String separator) {
        super("use the separator, which is '" + separator + "'");
    }
}
