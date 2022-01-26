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

    public Deadline(String description, LocalDate deadlineDate, LocalTime time) {
        super(description);
        this.deadlineDate = deadlineDate;
        this.day = deadlineDate.getDayOfWeek();
        this.month = deadlineDate.getMonth();
        this.year = deadlineDate.getYear();
        this.time = time;
    }

    @Override
    public String toStringForSave() {
        return "D "+ super.toStringForSave() + " # " + this.deadlineDate + " " + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.month + " "
                + this.day + " "
                + this.year + " "
                + this.time + ")";
    }

}
