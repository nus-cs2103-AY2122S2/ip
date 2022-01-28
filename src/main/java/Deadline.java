package duke;

public class Deadline extends Task {

    public Deadline(String description) {
        super(description);
    }
    public String toString() {
        return String.format("[D][%s] %s", getStatusIcon(), description);
    }
}
