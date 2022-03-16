package duke.dukeexception;

public class DukeNoTimeGivenException extends DukeException {
    public DukeNoTimeGivenException() {
        super("specify the time please\n");
    }
}
