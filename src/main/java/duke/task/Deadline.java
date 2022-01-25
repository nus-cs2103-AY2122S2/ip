package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static duke.constant.Message.CLOSE_BRACKET;
import static duke.constant.Message.OPEN_BRACKET;
import static duke.constant.TaskConstant.PREFIX_DEADLINE;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        this.by = LocalDate.parse(by.strip());
    }

    public String getBy() {
        return by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + PREFIX_DEADLINE + CLOSE_BRACKET + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
