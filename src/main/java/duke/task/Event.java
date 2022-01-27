package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {
    private final Date time;

    public Event(String title, Date time, boolean isComplete) {
        super(title, isComplete);
        this.time = time;
    }

    public Event(String title, Date time) {
        super(title, false);
        this.time = time;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yy HH:mm");
        return "[E]" + super.toString() + "(at: " + formatter.format(time) + ")";
    }
}
