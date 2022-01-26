package duke;

import java.util.ArrayList;
import java.time.LocalDateTime;

<<<<<<< HEAD:src/main/java/duke/DeadLines.java
/**
 * Inherits from Task and is the implementation of a Deadline task which contains a description and a deadline
 */
public class DeadLines extends Task {
    LocalDateTime deadline;

    /**
     * Constructor for the Deadline task
     * @param task Description of task
     * @param deadline Deadline of the task
     * @throws DukeException if the format of the deadline is not correct or description is empty
     */
    public DeadLines(String task, String deadline) throws DukeException {
=======
public class Deadlines extends Task {
    LocalDateTime deadline;

    public Deadlines(String task, String deadline) throws DukeException {
>>>>>>> A-CodingStandard:src/main/java/duke/Deadlines.java
        super(task);
        if (deadline == null) {
            throw new DukeException("Try Again with correct format!\n");
        }
        this.deadline = formatDateTime(deadline);
        if (this.deadline == null) {
            throw new DukeException("Incorrect Format/Date or Time is out of range\n");
        }
        this.initials = "D";
        if (task.length() < 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        }
    }

    /**
     * Converts the task into a readable form
     * @return String containing information on this deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: Date-> " + this.deadline.getDayOfMonth()
                                             + " Month-> " + this.deadline.getMonth()
                                             + " Day-> " + this.deadline.getDayOfWeek()
                                             + " Year-> " + this.deadline.getYear()
                                             + " Time-> " + this.deadline.getHour()
                                             + this.deadline.getMinute() + ")";
    }

    /**
     * Converts the task into a compact version for storage
     * @return String containing compact version of the task
     */
    @Override
    public ArrayList<String> makeCompact() {
        ArrayList<String> out = super.makeCompact();
        out.add(deadline.toString().replaceFirst("T", " ").replaceAll("-", "/"));
        return out;
    }
}
