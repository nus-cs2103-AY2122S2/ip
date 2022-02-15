package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

/**
 * Represents an event task. An event is something that happens on a specific date.
 */
public class Event extends Task {
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected LocalDate at;
    protected LocalDateTime atTime;

    /**
     * Instantiates a new Event object.
     * An exception is thrown if the program fails to parse the date/datetime provided.
     * @param description Description of the event. (E.g. company meeting)
     * @param at Date/Datetime of the event.
     * @throws DukeException if the program is unable to parse the date/datetime field.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        if (isDateFormat(at)) {
            this.at = LocalDate.parse(at, dateFormat);
        } else if (isDateTimeFormat(at)) {
            this.atTime = LocalDateTime.parse(at, dateTimeFormat);
        } else {
            throw new DukeException("The date format parsed is incorrect!" + ""
                    + "It should be dd-MM-yyyy or dd-MM-yyyy HH:mm!");
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
     * A method that takes in a string and checks if its in dateTime format.
     * Exception will be thrown if LocalDateTime library is unable to parse the string.
     * @param dateTimeString Date/Datetime of the task in string form.
     * @return A boolean that indicates if the string is in dateTime format.
     */
    private boolean isDateTimeFormat(String dateTimeString) {
        try {
            LocalDateTime.parse(dateTimeString, dateTimeFormat);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Checking DateTime Format!");
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
        } catch (DateTimeParseException e) {
            System.out.println("Checking Date Format!");
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
