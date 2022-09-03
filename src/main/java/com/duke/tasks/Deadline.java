package com.duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructor for a Deadline Task object.
     * @param description Description of the task.
     * @param dateTime Date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public String getBy() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd YYYY K:mma"));
    }

    @Override
    public String getSaveDescription() {
        return String.format("%s | %s | %s | %s",
                getClass().getSimpleName(), isDone == true ? 1 : 0, description, dateTime.toString());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getBy());
    }
}
