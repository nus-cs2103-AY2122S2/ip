package echo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class inherits from Task class and encapsulates a deadline task.
 */
public class DeadlineTask extends Task {

    /** Deadline of task. */
    private final LocalDateTime BY;

    /**
     * Constructor for DeadlineTask class.
     *
     * @param desc Description of DeadlineTask.
     * @param by Deadline of DeadlineTask.
     */
    public DeadlineTask(String desc, LocalDateTime by) {
        super(desc);
        this.BY = by;
    }

    /**
     * Returns string representation of DeadlineTask for saving.
     *
     * @return String representation of DeadlineTask for saving.
     */
    @Override
    public String saveFormat() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
        return "D | " + super.saveFormat() + " | " + this.BY.format(format);
    }

    /**
     * Returns string representation of DeadlineTask.
     *
     * @return String representation of DeadlineTask.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "[D]" + super.toString() + " (by: " + this.BY.format(formatDateTime) + ")";
    }

    /**
     * Checks if instances of DeadlineCommand are equal.
     *
     * @param obj Object.
     *
     * @return If the DESCRIPTION and BY are equal, returns true; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeadlineTask) {
            // Since obj is an instanceof DeadlineCommand, it is safe to type cast
            // Object to DeadlineCommand.
            DeadlineTask deadlineTask = (DeadlineTask) obj;
            boolean isDescriptionEqual = super.equals(deadlineTask);
            boolean isDateTimeEqual = this.BY.equals(deadlineTask.BY);
            return isDescriptionEqual && isDateTimeEqual;
        }
        return false;
    }
}
