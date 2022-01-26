package duke.task;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String taskDescriptionForFile() {
        int i = super.isDone ? 1 : 0;
        return "E , " + i + " , "
                + this.description.trim() + " , " + this.at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(super.outputDateFormat) + ")";
    }
}