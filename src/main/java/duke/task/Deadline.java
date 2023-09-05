package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate date) {
        super(description);
        this.by = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Gets format to display deadline task on file
     *
     * @return format to display deadline task on file
     */
    @Override
    public String getFileFormat() {
        return "D" + super.getFileFormat() + " | " + by;
    }

    /**
     * Updates date based on content given
     *
     * @param content to replace date section
     */
    @Override
    public void updateDate(String content) {
        if (!isDate(content)) {
            this.by = content;
        } else {
            this.by = LocalDate.parse(content).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
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
     * Gets general format to display deadline task
     *
     * @return general format to display deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}