package duke;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Deadlines extends Task {
    LocalDateTime deadline;

    public Deadlines(String task, String deadline) throws DukeException {
        super(task);
        if (deadline == null) {
            throw new DukeException("Try Again with correct format!\n");
        }
        this.deadline = formatDateTime(deadline);
        if (this.deadline == null) {
            throw new DukeException("Incorrect format\n");
        }
        this.initials = "D";
        if (task.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: Date-> " + this.deadline.getDayOfMonth()
                                             + " Month-> " + this.deadline.getMonth()
                                             + " Day-> " + this.deadline.getDayOfWeek()
                                             + " Year-> " + this.deadline.getYear()
                                             + " Time-> " + this.deadline.getHour()
                                             + this.deadline.getMinute() + ")";
    }

    @Override
    public ArrayList<String> makeCompact() {
        ArrayList<String> out = super.makeCompact();
        out.add(deadline.toString().replaceFirst("T", " ").replaceAll("-", "/"));
        return out;
    }
}
