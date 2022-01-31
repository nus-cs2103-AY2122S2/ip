package dukeclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event that has a description and the deadline of the event.
 */
public class Event extends Task {
    private LocalDate deadline;

    /**
     * Class constructor of Event.
     *
     * @param description String that describes Event.
     * @param deadline LocalDate indicating the date Event is due on.
     */
    public Event(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns a string of the identity of the Event(i.e. its description and due Date).
     * Identity used in taskList.
     *
     * @return String to identify the Event.
     */
    @Override
    public String identify() {
        String dateString = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy" ));
        if (super.getIsDone()) {
            return String.format("[E][X] %s (by: %s)\n", super.getDescription(), dateString);
        } else {
            return String.format("[E][ ] %s (by: %s)\n", super.getDescription(), dateString);
        }
    }

}
