package DukeBot;

public class Event extends Task {
    String time;

    public Event(String description, String time) {
        super("E", description);
        this.time = time;
    }

    public String toString() {
        return super.toString() + "(at: " + time + ")";
    }

}
