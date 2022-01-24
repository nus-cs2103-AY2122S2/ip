package kidsnd274.duke.taskobjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.time.temporal.ChronoUnit;

abstract class TaskWithDate extends Task {
    private final LocalDate date;

    public TaskWithDate(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date);
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return date.format(formatter);
    }
}
