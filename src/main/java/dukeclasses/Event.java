package dukeclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event that has a description and the deadline of the event.
 */
public class Event extends Task {
    private LocalDate deadline;
    private boolean isRecurring;
    private RecurPeriod recurPeriod;

    /**
     * Class constructor of Event.
     *
     * @param description String that describes Event.
     * @param deadline LocalDate indicating the date Event is due on.
     */
    public Event(String description, LocalDate deadline, RecurPeriod recurPeriod) {
        super(description);
        this.deadline = deadline;
        this.recurPeriod = recurPeriod;
        if (recurPeriod != null) {
            this.isRecurring = true;
        } else {
            this.isRecurring = false;
        }
    }

    /**
     * Increases the dateline based on the recurring period in RecurPeriod and
     * sets the task as unmarked.
     */
    public void recur() {
        if (isRecurring) {
            deadline = deadline.plus(recurPeriod.getPeriod());
            super.setDone(false);
        }
    }
    /**
     * Returns a string of the identity of the Event(i.e. its description and due Date).
     * Identity used in taskList.
     *
     * @return String to identify the Event.
     */
    @Override
    public String toString() {
        String dateString = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String output = "";
        if (super.getIsDone()) {
            output = "[E][X]";

        } else {
            output = "[E][ ]";
        }
        if (isRecurring) {
            return String.format("%s %s every %s (on: %s)\n",
                    output, super.getDescription(), recurPeriod.toString(), dateString);
        } else {
            return String.format("%s %s (on: %s)\n", output, super.getDescription(), dateString);
        }
    }

}
