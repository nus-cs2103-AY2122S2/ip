package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.TaskException;

/**
 * Represents a deadline task. A deadline is something that needs to be completed by a
 * specific date.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter OUTPUT_DATE = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private static final DateTimeFormatter OUTPUT_DATETIME = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm");
    private static final DateTimeFormatter INPUT_DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter INPUT_DATETIME = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    protected LocalDate by;
    protected LocalDateTime byTime;

    /**
     * Instantiates a new Deadline object.
     * An exception is thrown if the program fails to parse the date/datetime provided.
     * @param description Description of the deadline. (E.g. company meeting)
     * @param by Date/Datetime of the deadline.
     * @throws TaskException if the program is unable to parse the date/datetime field.
     */
    public Deadline(String description, String by) throws TaskException {
        super(description);
        if (isDateFormat(by)) {
            this.by = LocalDate.parse(by, INPUT_DATE);
        } else if (isDateTimeFormat(by)) {
            this.byTime = LocalDateTime.parse(by, INPUT_DATETIME);
        } else if (isFileDateFormat(by)) {
            this.by = LocalDate.parse(by, OUTPUT_DATE);
        } else if (isFileDatetimeFormat(by)) {
            this.byTime = LocalDateTime.parse(by, OUTPUT_DATETIME);
        } else {
            throw new TaskException("INVALID_DATE_FORMAT");
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
     * A method that takes in a string and check if its in input date format.
     * Exception will be thrown if LocalDate library is unable to parse the string.
     * @param dateString Date/Datetime of the task in string form.
     * @return A boolean that indicates if a string is in input date format.
     */
    private boolean isDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString, INPUT_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * A method that takes in a string and check if its in output date format.
     * Exception will be thrown if LocalDate library is unable to parse the string.
     * @param dateString Date/Datetime of the task in string form.
     * @return A boolean that indicates if a string is in output date format.
     */
    private boolean isFileDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString, OUTPUT_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * A method that takes in a string and checks if its in input dateTime format.
     * Exception will be thrown if LocalDateTime library is unable to parse the string.
     * @param dateTimeString Date/Datetime of the task in string form.
     * @return A boolean that indicates if the string is in input dateTime format.
     */
    private boolean isDateTimeFormat(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, INPUT_DATETIME);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * A method that takes in a string and checks if its in output dateTime format.
     * Exception will be thrown if LocalDateTime library is unable to parse the string.
     * @param dateTimeString Date/Datetime of the task in string form.
     * @return A boolean that indicates if the string is in output dateTime format.
     */
    private boolean isFileDatetimeFormat(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, OUTPUT_DATETIME);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns a string representing a formatted date-time given a LocalDateTime object.
     * @param dateTime LocalDateTime object.
     * @return String format of the LocalDateTime object.
     */
    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_DATETIME);
    }

    /**
     * Returns a string representing a formatted date given a LocalDate object.
     * @param date LocalDate object.
     * @return String format of the LocalDate object.
     */
    public String formatDate(LocalDate date) {
        return date.format(OUTPUT_DATE);
    }

}

