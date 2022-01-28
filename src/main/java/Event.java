package duke;

public class Event extends Task{

    public Event(String description) {
        super(description);
    }

    public String toString() {
        return String.format("[E][%s] %s", getStatusIcon(), description);
    }
}
