package duke.task;

import duke.DateHelper;

public class Event extends Task {
    protected DateHelper at;
    private String type;

    public Event(String description, DateHelper date) {
        super(description);
        at = date;
        type = "E";
    }

    @Override
    public String getTask() {
        return "[" + type + "]" + super.getTask() + " (at: " + at.getDatetime() + ")";
    }

    @Override
    public String getDescription() {
        return type + " | " + description + " | " + at.getDatetime();
    }
}