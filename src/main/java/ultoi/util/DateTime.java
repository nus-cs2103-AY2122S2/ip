package ultoi.util;

import java.time.LocalDate;

/**
 * Represents a data and time.
 *
 * @author snoidetx
 * @version 0.0
 */
public class DateTime implements Comparable<DateTime> {
    private final LocalDate date;
    private final int time;

    /**
     * Creates a new DateTime object.
     *
     * @param datetime Input string to be processed.
     * @throws UltoiException If the input string does not follow the format "YYYY-MM-DD tttt".
     */
    public DateTime(String dateTime) throws UltoiException {
        String[] dateTimes = dateTime.split(" ");

        try {
            this.date = LocalDate.parse(dateTimes[0]);
            this.time = Integer.parseInt(dateTimes[1]);
        } catch (Exception e) {
            throw UltoiException.wrongDateTimeFormatException();
        }

        if (this.time < 0 || time % 100 > 59 || time / 100 > 23) {
            throw UltoiException.wrongDateTimeFormatException();
        }
    }

    @Override
    public int compareTo(DateTime ot) {
        if (!this.date.isEqual(ot.date)) {
            return this.date.compareTo(ot.date);
        } else {
            return this.time - ot.time;
        }
    }

    /**
     * Returns a string representing the standard input for this DateTime object.
     *
     * @return Input string of this DateTime object.
     */
    public String toInputString() {
        return this.date.toString() + " "
                + (this.time > 999 ? "" : "0") + time;
    }

    /**
     * Returns a string representation of this DateTime object.
     *
     * @return String representation of this DateTime object.
     */
    @Override
    public String toString() {
        return this.date.toString() + " " + (this.time > 999 ? "" : "0")
                + this.time / 100 + ":" + (this.time % 100 > 9 ? "" : "0") + this.time % 100;
    }
}
