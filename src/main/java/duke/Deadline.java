package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Represents a <code>Task</code> with a deadline date
 */
public class Deadline extends Task {
    private String deadlineDate;
    private LocalDate date;

    Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
        try {
            new SimpleDateFormat("yyyy-mm-dd").parse(deadlineDate);
            this.date = DateParser.parseDate(deadlineDate);
        } catch (ParseException e) {
            this.date = null;
        }
    }

    Deadline(String description, String deadlineDate, Boolean completed) {
        super(description, completed);
        this.deadlineDate = deadlineDate;
        try {
            new SimpleDateFormat("yyyy-mm-dd").parse(deadlineDate);
            this.date = DateParser.parseDate(deadlineDate);
        } catch (ParseException e) {
            this.date = null;
        }
    }

    /**
     * Obtains the type of <code>Task</code>
     * @return String corresponding to the type of <code>Task</code>
     */
    @Override
    public String getType() {
        return "Deadline";
    }

    /**
     * Obtains the due date of the <code>Deadline</code> object
     * @return String corresponding to the deadline date of the <code>Task</code>
     */
    public String getTime() {
        return this.deadlineDate;
    }

    /**
     * Obtains the string representation of the <code>Deadline</code> object
     * @return String corresponding to the <code>Deadline</code> object
     */
    @Override
    public String toString() {
        String printedDate;
        if (date != null) {
            printedDate = DateParser.dateToString(this.date);
        } else {
            printedDate = this.deadlineDate;
        }
        return "[D]" + super.toString() + " (by: " + printedDate + ")";
    }
}
