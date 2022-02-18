package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.TaskException;

/**
 * Represents an event task. An event is something that happens on a specific date.
 */
public class Event extends Task {
    private static final DateTimeFormatter OUTPUT_DATE = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private static final DateTimeFormatter OUTPUT_DATETIME = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm");
    private static final DateTimeFormatter INPUT_DATE = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter INPUT_DATETIME = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected LocalDate at;
    protected LocalDateTime atTime;

    /**
     * Instantiates a new Event object.
     * An exception is thrown if the program fails to parse the date/datetime provided.
     * @param description Description of the event. (E.g. company meeting)
     * @param at Date/Datetime of the event.
     * @throws TaskException if the program is unable to parse the date/datetime field.
     */
    public Event(String description, String at) throws TaskException {
        super(description);
        if (isDateFormat(at)) {
            this.at = LocalDate.parse(at, INPUT_DATE);
        } else if (isDateTimeFormat(at)) {
            this.atTime = LocalDateTime.parse(at, INPUT_DATETIME);
        } else if (isFileDatetimeFormat(at)) {
            this.atTime = LocalDateTime.parse(at, OUTPUT_DATETIME);
        } else if (isFileDateFormat(at)) {
            this.at = LocalDate.parse(at, OUTPUT_DATE);
        } else {
            throw new TaskException("INVALID_DATE_FORMAT");
        }
    }

    /**
     * Method that converts a event to its storage file format.
     * This format is what you will see in the storage file stored in the data folder.
     * @return A string that describes a deadline with its storage file format.
     */
    @Override
    public String toFileFormat() {
        if (at != null) {
            return "E" + super.toFileFormat() + "," + formatDate(at);
        } else {
            return "E" + super.toFileFormat() + "," + formatDateTime(atTime);
        }
    }

    /**
     * Method that converts an event to its UI format.
     * This format is what will be shown to the user on the GUI.
     * @return A string that describes a deadline with its UI format.
     */
    @Override
    public String toString() {
        if (this.at != null) {
            return "[E]" + super.toString() + " (at: " + formatDate(at) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + formatDateTime(atTime) + ")";
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
     * The date will be formatted in dd-MMM-yyyy HH:mm format.
     * @param dateTime LocalDateTime object.
     * @return String format of the LocalDateTime object.
     */
    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_DATETIME);
    }

    /**
     * Returns a string representing a formatted date given a LocalDate object.
     * The date will be formatted in dd-MMM-yyyy format.
     * @param date LocalDate object.
     * @return String format of the LocalDate object.
     */
    public String formatDate(LocalDate date) {
        return date.format(OUTPUT_DATE);
    }

}
