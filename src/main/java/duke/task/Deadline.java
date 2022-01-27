package duke.task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private final Date time;

    public Deadline(String title, Date time, boolean isComplete) {
        super(title, isComplete);
        this.time = time;
    }

    public Deadline(String title, Date time) {
        super(title, false);
        this.time = time;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yy HH:mm");
        return "[D]" + super.toString() + "(by: " + formatter.format(time) + ")";
    }
}
