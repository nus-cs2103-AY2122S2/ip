package duke.task;

public class Event extends Task {
    String at;
    public Event(String activity, String at) {
        super(activity, "E");
        this.at = at;
    }

    @Override
    public String printTask() {
        if (this.status == 0) {
            return "[" + type + "][ ] " + activity + " (at " + at + ")";
        } else {
            return "[" + type + "][X] " + activity + " (at " + at + ")";
        }
    }

    @Override
    public String toString() {
        return type + "|" + status + "|" + activity + "|" + at + "|\n";
    }

}
