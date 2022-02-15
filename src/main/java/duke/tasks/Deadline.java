package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;

/**
 * Represents a deadline task. A deadline is something that needs to be completed by a
 * specific date.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    protected LocalDate by;
    protected LocalDateTime byTime;

    /**
     * Instantiates a new Deadline object.
     * An exception is thrown if the program fails to parse the date/datetime provided.
     * @param description Description of the deadline. (E.g. company meeting)
     * @param by Date/Datetime of the deadline.
     * @throws DukeException if the program is unable to parse the date/datetime field.
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
     * Method that converts a deadline to its storage file format.
     * This format is what you will see in the storage file stored in the data folder.
     * @return A string that describes a deadline with its storage file format.
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
     * Method that converts a deadline to its UI format.
     * This format is what will be shown to the user on the GUI.
     * @return A string that describes a deadline with its UI format.
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
     * A method that takes in a string and checks if its in dateTime format.
     * Exception will be thrown if LocalDateTime library is unable to parse the string.
     * @param dateTimeString Date/Datetime of the task in string form.
     * @return A boolean that indicates if the string is in dateTime format.
     */
    private boolean isDateTimeFormat(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, dateTimeFormat);
            return true;
        } catch (Exception e) {
            // Empty catch block as the dateString could also be in date format
        }
        return false;
    }

    /**
     * A method that takes in a string and check if its in date format.
     * Exception will be thrown if LocalDate library is unable to parse the string.
     * @param dateString Date/Datetime of the task in string form.
     * @return A boolean that indicates if a string is in date format.
     */
    private boolean isDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString, dateFormat);
            return true;
        } catch (Exception e) {
            // Empty catch block as the dateString could also be in datetime format
        }
        return false;
    }

    /**
     * Returns a string representing a formatted date-time given a LocalDateTime object.
     * @param dateTime LocalDateTime object.
     * @return String format of the LocalDateTime object.
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
     * Returns a string representing a formatted date given a LocalDate object.
     * @param date LocalDate object.
     * @return String format of the LocalDate object.
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

