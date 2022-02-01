package jose.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor that sets isDone to false.
     *
     * @param description Task description.
     */
    public Deadline(String description, String by) {
        super(description, false);
        String[] dateTime = by.split(" ");
        this.by = LocalDateTime.of(LocalDate.parse(dateTime[0]),
                LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("HHmm")));
    }

    /**
     * Constructor that sets all variables.
     *
     * @param description Task description
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Returns a formatted representation of a task that will be saved to the data file.
     *
     * @return A formatted string to be saved to the data file.
     */
    public String formatData() {
        return "D|" + super.formatData() + "|" + by;
    }

    /**
     * Returns a string representation of a task.
     *
     * @return A string representation of a task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma")) + ")";
    }
}
