package duke.task;

import java.time.LocalDateTime;

/**
 * Represents the event (with date) entered by user
 */
public class Event extends Task {

    protected LocalDateTime at;
    private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    /**
     * Constructor for object Event
     *
     * @param at the date of event
     * @param description description for the task
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for object Event
     *
     * @param description description for the task
     * @param isDone whether the task is done
     * @param at date of event
     */
    public Event(String description, int isDone, LocalDateTime at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns the time of the event happening
     *
     * @return LocalDateTime of the event
     */
    public LocalDateTime getAt() {
        return at;
    }

    @Override
    public String toString() {
        int year = at.getYear();
        String month = months[at.getMonthValue() - 1];
        int day = at.getDayOfMonth();

        String hour;
        if (at.getHour() < 10) {
            hour = "0" + at.getHour();
        } else {
            hour = String.valueOf(at.getHour());
        }

        String min;
        if (at.getMinute() < 10) {
            min = "0" + at.getMinute();
        } else {
            min = String.valueOf(at.getMinute());
        }

        return "[E]"
                + super.toString()
                + " (at: " + month + " " + day + " " + year + " " + hour + ":" + min + ")";
    }
}
