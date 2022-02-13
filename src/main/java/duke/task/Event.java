package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task
 */
public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, LocalDate date) {
        super(description);
        this.at = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Gets format to display event task on file
     *
     * @return format to display event task on file
     */
    @Override
    public String getFileFormat() {
        return "E" + super.getFileFormat() + " | " + at;
    }

    /**
     * Updates date based on content given
     *
     * @param content to replace date section
     */
    @Override
    public void updateDate(String content) {
        if (!isDate(content)) {
            this.at = content;
        } else {
            this.at = LocalDate.parse(content).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        }
    }

    /**
     * Validates whether string input can be parsed into local date
     *
     * @param input string input typed by user
     * @return true if string input can be parsed into local date, otherwise false
     */
    public static boolean isDate(String input) {
        try {
            LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Gets general format to display event task
     *
     * @return general format to display event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}