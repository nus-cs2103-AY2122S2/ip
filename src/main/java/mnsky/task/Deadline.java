package mnsky.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String by;
    private LocalDate byDate;
    private LocalTime byTime;

    public Deadline(String taskName, String by, LocalDate byDate, LocalTime byTime) {
        super(taskName);
        this.by = by;
        this.byDate = byDate;
        this.byTime = byTime;
    }

    @Override
    public String toString() {
        String genericTaskname = super.getGenericTaskName();
        String dateString = "";
        String timeString = "";
        boolean dateExists = this.byDate != null;
        boolean timeExists = this.byTime != null;

        if (dateExists) {
            dateString = this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }

        if (timeExists) {
            timeString = this.byTime.format(DateTimeFormatter.ofPattern("h:mm a"));
        }

        if (dateExists && timeExists) {
            return String.format("[D]%s (by: %s, %s)", genericTaskname, dateString, timeString);
        } else if (dateExists) {
            return String.format("[D]%s (by: %s)", genericTaskname, dateString);
        } else if (timeExists) {
            return String.format("[D]%s (by: %s)", genericTaskname, timeString);
        } else {
            return String.format("[D]%s (by: %s)", genericTaskname, this.by);
        }
    }

    @Override
    public String getSaveData() {
        return String.format("[D]%s /by %s",  super.getGenericTaskName(), this.by);
    }
}
