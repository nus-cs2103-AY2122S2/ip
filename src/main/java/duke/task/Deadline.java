package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate byDate;
    protected LocalTime byTime;

    public Deadline(String description, boolean isDone, LocalDate byDate, LocalTime byTime) {
        super(description, isDone);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    protected String getDateTime() {
        return byDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " "
                + byTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }


    public String getType() {
        return  "D";
    }

    @Override
    public String formatToSave() {
        return super.formatToSave() + "|" + byDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " "
                + byTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    };

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDateTime() + ")";
    }
}