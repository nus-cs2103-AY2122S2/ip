package task;

import java.time.LocalDate;

/**
 * Class that encapsulates event tasks with due dates.
 */
public class Event extends Task {

    LocalDate date;

    public Event(String details, String date) {
        super(details);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns character symbol that represents event tasks.
     * @return String 'E'.
     */
    public String symbol() {
        return "E";
    }

    /**
     * Returns event details along with full string representation of the date.
     * @return details of the event.
     */
    @Override
    public String displayTime() {
        return super.toString() + this.date.getDayOfMonth() + " " + this.date.getMonth() + " " + this.date.getYear();
    }

    /**
     * Returns details of the event along with date object.
     * @return String which combines event details with toString method of the date object.
     */
    @Override
    public String toString() {
        return super.toString() + "/" + this.date.toString();
    }
}
