package com.duke.task;

import com.duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline (String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public LocalDate getDate() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
