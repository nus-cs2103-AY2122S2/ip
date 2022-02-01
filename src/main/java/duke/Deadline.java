package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

public class Deadline extends Task {

    protected LocalDate deadlineDate;
    protected DayOfWeek day;
    protected Month month;
    protected int year;
    protected LocalTime time;

    /**
     * Constructs a Deadline object.
     *
     * @param description description of the deadline task.
     * @param deadlineDate deadline date.
     * @param time deadline time.
     */
    public Deadline(String description, LocalDate deadlineDate, LocalTime time) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.day = deadlineDate.getDayOfWeek();
        this.month = deadlineDate.getMonth();
        this.year = deadlineDate.getYear();
        this.time = time;
    }

    /**
     * Returns a string representation to write to disk.
     *
     * @return
     */
    @Override
    public String toStringForSave() {
        return "D " + super.toStringForSave() + " # " + this.deadlineDate + " " + this.time;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return string representation of Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")";
    }

}
