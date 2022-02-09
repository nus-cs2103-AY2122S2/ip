package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.constant.Message.CLOSE_BRACKET;
import static duke.constant.Message.OPEN_BRACKET;
import static duke.constant.TaskConstant.PREFIX_DEADLINE;

/**
 * A class represents for a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");


    /**
     * Class constructor with the description and the date time following yyyy-MM-dd format
     * Creates an undone Deadline.
     *
     * @param description Deadline description
     * @param by Deadline date time
     * @throws DateTimeException error when wrong format date time
     */
    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        this.by = LocalDateTime.parse(by.strip(), inputFormatter);
    }

    /**
     * Returns the date of Deadline
     * @return Deadline date in yyyy-mm-dd format
     */
    public String getBy() {
        return by.format(inputFormatter);
    }

    public LocalDateTime getDateInLocalDateTime() {
        return by;
    }

    /**
     * Overrides toString method to make a string including prefix, status icon, description and date.
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        return OPEN_BRACKET + PREFIX_DEADLINE + CLOSE_BRACKET + super.toString() + " (by: " + by.format(outputFormatter) + ")";
    }
}
