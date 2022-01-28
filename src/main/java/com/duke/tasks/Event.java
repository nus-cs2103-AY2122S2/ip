package com.duke.tasks;

public class Event extends Task {
    protected String timeDate;

    public Event(String description, String date) {
        super(description);
        this.timeDate = date;
    }

    public String getTimeDate() {
        return timeDate;
    }

    @Override
    public String getSaveDescription() {
        return String.format("%s | %s | %s | %s",
                getClass().getName(), status == true ? 1 : 0, description, getTimeDate());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), getTimeDate());
    }
}
