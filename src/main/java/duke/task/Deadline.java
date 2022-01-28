package duke.task;

import java.time.LocalDateTime;

/**
 * Represents the Duke.task (with deadline) entered by user
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    /**
     * Constructor for object Deadline
     *
     * @param by the due date of deadline
     * @param description description for the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for object Deadline
     *
     * @param description description for the task
     * @param isDone whether the task is done
     * @param by due date of deadline
     */
    public Deadline(String description, int isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the time of deadline
     *
     * @return LocalDateTime deadline
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        int year = by.getYear();
        String month = months[by.getMonthValue() - 1];
        int day = by.getDayOfMonth();

        String hour;
        if (by.getHour() < 10) {
            hour = "0" + by.getHour();
        } else {
            hour = String.valueOf(by.getHour());
        }

        String min;
        if (by.getMinute() < 10) {
            min = "0" + by.getMinute();
        } else {
            min = String.valueOf(by.getMinute());
        }

        return "[D]"
                + super.toString()
                + "(by: " + month + " " + day + " " + year + " " + hour + ":" + min + ")";
    }
}
