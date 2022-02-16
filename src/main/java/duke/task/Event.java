package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.utils.Priority;

public class Event extends Task {
    private LocalDateTime time;

    /**
     * Creates an Event with the given description and time.
     *
     * @param description The name of the event.
     * @param time The time of the event.
     * @param priority The Priority object specifying the priority of this Event.
     */
    public Event(String description, LocalDateTime time, Priority priority) {
        super(description, TaskType.EVENT, priority);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return String.format("%s (at: %shrs)", super.toString(), time.format(format));
    }

    @Override
    public String getFileSaveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        return String.format("%s | %s", super.getFileSaveFormat(), time.format(format));
    }
}
