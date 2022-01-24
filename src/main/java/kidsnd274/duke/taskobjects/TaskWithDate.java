package kidsnd274.duke.taskobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;

public abstract class TaskWithDate extends Task {
    private final LocalDate date;

    public TaskWithDate(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date);
    }

    public TaskWithDate(String name, boolean isDone, String date) {
        super(name, isDone);
        this.date = LocalDate.parse(date);
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(formatter);
    }

    public String getDate() {
        return date.toString();
    }
}
