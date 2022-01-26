package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

public class Deadline extends Task {
    protected LocalDate dueDate;
    protected LocalTime time;
    protected String date;

    public Deadline(String description, String dueDate) throws DukeException {
        super(description);
        this.date = dueDate;
        try {
            this.setDueDateTime(dueDate);
        } catch (Exception e) {
            throw new DukeException("Wrong Date and Time format. yyyy-mm-dd HH:MM\n");
        }
    }

    private void setDueDateTime(String dueDate) {
        this.dueDate = LocalDate.parse(dueDate.split(" ")[0]);
        this.time = LocalTime.parse(dueDate.split(" ")[1]);
    }

    private String dateToString() {
        return this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    private String timeToString() {
        return this.time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    public String getDate() {
        return this.dateToString();
    }

    @Override
    public String getTaskData() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateToString() + " " + this.timeToString() + ")";
    }
}
