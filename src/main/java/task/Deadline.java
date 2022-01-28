package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected LocalDateTime ldt;
    public String by;

    public Deadline(String description, String by) {
        super(description);
        this.ldt = parseString(by);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT);
        return "[D]" + super.toString() + " (by: " + ldt.format(formatter) + ")";
    }

    private LocalDateTime parseString(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(by, formatter);

    }

    /**
     * Stores the task and its details in text form
     *
     * @return string to be stored in text form
     */

    public String storageString() {
        return String.format("[D][%s] %s %s", this.getStatusIcon(), this.description, this.by);
    }
}