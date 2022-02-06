package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a user's deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_OUT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter TIME_OUT = DateTimeFormatter.ofPattern("hh:mm a");
    protected LocalDate d;
    protected LocalTime t;
    private final boolean hasDate;
    private final boolean hasTime;

    /**
     * Creates a new deadline with a date.
     *
     * @param description the description of the deadline
     * @param d           the date of the deadline
     */
    public Deadline(String description, LocalDate d) {
        super(description);
        this.d = d;
        hasDate = true;
        hasTime = false;
    }

    /**
     * Creates a new deadline with a time.
     *
     * @param description the description of the deadline
     * @param t           the time of the deadline
     */
    public Deadline(String description, LocalTime t) {
        super(description);
        this.t = t;
        hasDate = false;
        hasTime = true;
    }

    /**
     * Creates a new deadline with a date and time.
     *
     * @param description the description of the deadline
     * @param d           the date of the deadline
     * @param t           the time of the deadline
     */
    public Deadline(String description, LocalDate d, LocalTime t) {
        super(description);
        this.d = d;
        this.t = t;
        hasDate = true;
        hasTime = true;
    }

    /**
     * Retrieves the date of the deadline.
     *
     * @return the date of the deadline formatted in "MMM dd YYYY"
     */
    private String getDate() {
        return d.format(DATE_OUT);
    }

    /**
     * Retrieves the time of the deadline.
     *
     * @return the time of the deadline formatted in "hh:mm am/pm"
     */
    private String getTime() {
        return t.format(TIME_OUT);
    }

    /**
     * Retrieves the date and time of the deadline.
     *
     * @return the date and time of the deadline formatted in "MMM dd YYYY hh:mm am/pm"
     */
    private String getDateTime() {
        return d.format(DATE_OUT) + " " + t.format(TIME_OUT);
    }

    /**
     * Checks if a date is present in the deadline.
     *
     * @return True if a date is present, false otherwise
     */
    @Override
    public boolean isHasDate() {
        return hasDate;
    }

    /**
     * Checks if a time is present in the deadline.
     *
     * @return True if a time is present, false otherwise
     */
    @Override
    public boolean isHasTime() {
        return hasTime;
    }

    /**
     * Produces the representation of the deadline in save file format.
     *
     * @return a string representation of the deadline to be used in storage
     */
    @Override
    public String getAppendData() {
        return "D | " + (super.isDone ? "1" : "0") + " | " + description + " | "
                + (hasDate ? d.toString() : "0") + " | "
                + (hasTime ? t.toString() : "0");
    }

    /**
     * Produces the deadline task using data from the save file.
     *
     * @param data an array containing the data
     * @return a deadline task containing the information from the data
     */
    public static Deadline getDeadlineFromData(String[] data) {
        String done = data[1];
        String description = data[2];
        String date = data[3];
        String time = data[4];

        Deadline d;
        if (!date.equals("0") && !time.equals("0")) {
            d = new Deadline(description,
                    LocalDate.parse(date),
                    LocalTime.parse(time));
        } else if (!date.equals("0")) {
            d = new Deadline(description, LocalDate.parse(date));
        } else {
            d = new Deadline(description, LocalTime.parse(time));
        }

        if (done.equals("1")) {
            d.markAsDone();
        }

        return d;
    }

    /**
     * Produces a string representation of the deadline.
     *
     * @return a string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (hasDate && hasTime
                        ? getDateTime()
                        : hasDate
                        ? getDate()
                        : getTime()) + ")";
    }
}
