package duke;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private final Date deadline;
    private final SimpleDateFormat ft = new SimpleDateFormat("dd MMM yyy h.mma");

    public Deadline(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, Date deadline, boolean completed) {
        super(description, completed);
        this.deadline = deadline;
    }

    /**
     * @inheritDoc
     */
    public String toFile() {
        System.out.println(this.deadline);
        return "D\t" + super.toFile() + "\t" + ft.format(this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ft.format(this.deadline) + ")";
    }
}
