package duke.task;

import duke.DateHelper;
import duke.task.Task;

public class Deadline extends Task {
    protected DateHelper by;
    private String type;

    public Deadline(String description, DateHelper date) {
        super(description);
        by = date;
        type = "D";
    }

    @Override
    public String getTask() {
        return "[" + type +"]" + super.getTask() + " (by: " + by.getDatetime() + ")";
    }

    @Override
    public String getDescription() {
        return type + " | " + description + " | " + by.getDatetime();
    }
}