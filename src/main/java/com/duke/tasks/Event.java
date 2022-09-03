package com.duke.tasks;

public class Event extends Task {
    protected String location;

    /**
     * Constructor for a Event Task object.
     * @param description Description of the task.
     * @param location Location of the event.
     */
    public Event(String description, String location) {
        super(description);
        this.location = location;
    }

    public String getTimeDate() {
        return location;
    }

    @Override
    public String getSaveDescription() {
        return String.format("%s | %s | %s | %s",
                getClass().getSimpleName(), isDone == true ? 1 : 0, description, getTimeDate());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getTimeDate());
    }
}
