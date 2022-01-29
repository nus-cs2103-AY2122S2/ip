package duke;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event extends Task {

    public Date duration;
    SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyy h.mma");

    public Event(String description, Date duration) {
        super(description);
        this.duration = duration;
    }

    public Event(String description, Date duration, boolean completed) {
        super(description, completed);
        this.duration = duration;
    }

    /**
     * @inheritDoc
     */
    public String toFile() {
        return "E\t" + super.toFile() + "\t" + ft.format(this.duration);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (at: " + ft.format(this.duration) + ")";
    }
}
