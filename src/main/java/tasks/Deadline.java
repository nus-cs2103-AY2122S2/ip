package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;

public class Deadline extends Task {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    protected LocalDate by;
    protected LocalDateTime byTime;



    /**
     * Instantiates a new Deadline object
     * @param description of the deadline
     * @param by (duedate of the task)
     * @throws DukeException if the date parsed is of an incorrect format
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        if (isDateFormat(by)) {
            this.by = LocalDate.parse(by, dateFormat);
        } else if (isDateTimeFormat(by)) {
            this.byTime = LocalDateTime.parse(by, dateTimeFormat);
        } else {
            throw new DukeException("The date format parsed is incorrect!"
                    + "It should be dd-MM-yyyy or dd-MM-yyyy HH:mm!");
        }
    }

    /**
     * Method that converts a deadline to its storage file format
     * @return A string that describes a deadline with its storage file format
     */

    @Override
    public String toFileFormat() {
        if (by != null) {
            return "D" + super.toFileFormat() + "," + formatDate(by);
        } else {
            return "D" + super.toFileFormat() + "," + formatDateTime(byTime);
        }
    }

    /**
     * Method that converts a deadline to its display/string format
     * @return A string that describes a deadline with its display format
     */
    @Override
    public String toString() {
        if (this.by != null) {
            return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + formatDateTime(byTime) + ")";
        }
    }

    /**
     * A method that takes in a string and checks if its in dateTime format
     * @param dateTimeString duedate string
     * @return A boolean that indicates if the string is in dateTime format
     */
    private boolean isDateTimeFormat(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, dateTimeFormat);
            return true;
        } catch (Exception e) {
            System.out.println("Checking DateTime Format!");
        }
        return false;
    }

    /**
     * A method that takes in a string and check if its in date format
     * @param dateString duedate string
     * @return A boolean that indicates if a string is in date format
     */
    private boolean isDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString, dateFormat);
            return true;
        } catch (Exception e) {
            System.out.println("Checking Date Format!");
        }
        return false;
    }

    /**
     * Takes in a dateTime object and converts it to the correct string display format
     * @param dateTime dateTime object
     * @return Formatted string containing details of the dateTime object
     */
    public String formatDateTime(LocalDateTime dateTime) {
        String day = dateTime.getDayOfMonth() < 10
                ? "0" + dateTime.getDayOfMonth()
                : "" + dateTime.getDayOfMonth();
        String month = dateTime.getMonthValue() < 10
                ? "0" + dateTime.getMonthValue()
                : "" + dateTime.getMonthValue();
        Integer year = dateTime.getYear();
        String hour = dateTime.getHour() < 10
                ? "0" + dateTime.getHour()
                : "" + dateTime.getHour();
        String minute = dateTime.getMinute() < 10
                ? "0" + dateTime.getMinute()
                : "" + dateTime.getMinute();
        return day + "-" + month + "-" + year + " " + hour + ":" + minute;
    }

    /**
     * Takes in a date object and converts it to the correct string display format
     * @param date date object
     * @return Formatted string containing details of the Date object
     */
    public String formatDate(LocalDate date) {
        String day = date.getDayOfMonth() < 10
                ? "0" + date.getDayOfMonth()
                : "" + date.getDayOfMonth();
        String month = date.getMonthValue() < 10
                ? "0" + date.getMonthValue()
                : "" + date.getMonthValue();
        Integer year = date.getYear();
        return day + "-" + month + "-" + year;
    }

}

