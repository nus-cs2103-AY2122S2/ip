package task;

import java.time.LocalDate;

/**
 * Class that encapsulates deadline tasks with due dates.
 */
public class Deadline extends Task {

    LocalDate date;

    public Deadline(String details, String date) {
        super(details);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns character symbol that represents deadline tasks.
     * @return String 'D'.
     */
    public String symbol() {
        return "D";
    }

    /**
     * Returns deadline details along with full string representation of the date.
     * @return details of the deadline.
     */
    @Override
    public String displayTime() {
        return super.toString() + this.date.getDayOfMonth() + " " + this.date.getMonth() + " " + this.date.getYear();
    }

    /**
     * Returns details of the deadline along with date object.
     * @return String which combines deadline details with toString method of the date object.
     */
    @Override
    public String toString() {
        return super.toString() + "/" + this.date.toString();
    }

}
