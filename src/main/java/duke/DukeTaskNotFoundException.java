package duke;

public class DukeTaskNotFoundException extends DukeException {
    @Override
    public String toString() {
        return "The task number keyed in is invalid.";
    }
}
