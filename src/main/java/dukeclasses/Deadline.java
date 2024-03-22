package dukeclasses;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a due date.
 */
public class Deadline extends Task {
    private LocalDate deadline;
    private boolean isRecurring;
    private RecurPeriod recurPeriod;

    /**
     * Constructor for Deadline.
     * @param description Describes the deadline.
     * @param deadline LocalDate that state the due date of instance of Deadline.
     */
    public Deadline(String description, LocalDate deadline, RecurPeriod recurPeriod) {
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
     * Returns a string of the identity of the Deadline(i.e. its description and due Date).
     * Identity used in taskList.
     *
     * @return String to identify the Deadline.
     */
    @Override
    public String toString() {
        String dateString = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String output = "";
        if (super.getIsDone()) {
            output = "[D][X]";
        } else {
            output = "[D][ ]";
        }
        if (isRecurring) {
            return String.format("%s %s every %s (by: %s)\n",
                    output, super.getDescription(), recurPeriod.toString(), dateString);
        } else {
            return String.format("%s %s (by: %s)\n", output, super.getDescription(), dateString);
        }
    }
}
