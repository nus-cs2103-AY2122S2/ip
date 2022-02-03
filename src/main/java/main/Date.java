package main;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represent a date.
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Date {
    private final LocalDate date;
    private final String time;

    /**
     * Create a Date object to represent a date.
     *
     * @param time A date in the format of "yyyy-mm-dd".
     */
    public Date(String time) {
        this.time = time;
        this.date = LocalDate.parse(time);
    }

    /**
     * Check if the input string can be converted to a date.
     *
     * @param time The string to be checked if it is in the format of "yyyy-mm-dd", e.g. 2020-12-02.
     * @throws TesseractException If the input string cannot be converted to a valid date.
     */
    public static void checkValidTime(String time) throws TesseractException {
        if (time.length() != 10) { // check if the string is of length 10
            throw new TesseractException("Please enter date in the YYYY-MM-DD format~");
        } else {
            try {
                String[] ints = time.split("-", 3);
                for (String integer : ints) {
                    Integer.parseInt(integer);
                }
                new Date(time).formattedTime();
            } catch (DateTimeException e) {
                throw new TesseractException("Please enter a valid date that can be found on calender :P");
            } catch (NumberFormatException e) {
                throw new TesseractException("Please enter date in the YYYY-MM-DD format~");
            }
        }
    }

    /**
     * Returns if the input date represents the same date as the Date instance.
     *
     * @param date1 The input date to compare with the Date instance.
     * @return True if the input date is the same date as the Date instance, false otherwise.
     */
    public boolean equals(Date date1) {
        return this.date.equals(date1.date);
    }

    /**
     * Return a string of date in the form of "MMM d yyyy", e.g. Mon 15 2020.
     *
     * @return The date of the instance in the "MMM d yyyy" format.
     */
    public String formattedTime() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Return a string representing the Date instance.
     *
     * @return A string representing the Date instance.
     */
    public String toString() {
        return this.time;
    }
}
