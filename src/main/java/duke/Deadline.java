package duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private Date date;
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");

    public Deadline(String description, Date date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, boolean hasCompleted, Date date) {
        super(description);
        this.status = hasCompleted;
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + Type.D + "]";
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + formatter.format(date).toString() + ")";
    }
}