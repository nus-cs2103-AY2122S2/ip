package seedu.duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    private final String taskType = "E";
    
    public Event(String taskName, LocalDateTime date) {
        super(taskName, false, date);
    }
    
    Event(Event oldEvent, boolean done) {
        super(oldEvent.getTaskName(), done, oldEvent.getDate());
    }

    public Event(String taskName, boolean doneStatus, LocalDateTime date) {
        super(taskName,doneStatus, date);
    }

    @Override
    public Task changeTaskStatus(boolean status) {
        return new Event(this, status);
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format(" (at: %s)",this.getFormattingDateString());
    }
}
