package duke;

public class DukeFileNotFoundException extends DukeException {
    @Override
    public String toString() {
        return "Backup file is not found.";
    }
}
